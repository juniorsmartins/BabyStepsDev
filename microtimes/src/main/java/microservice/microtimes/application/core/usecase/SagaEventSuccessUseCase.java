package microservice.microtimes.application.core.usecase;

import microservice.microtimes.adapter.mapper.MapperIn;
import microservice.microtimes.adapter.utils.JsonUtil;
import microservice.microtimes.application.core.domain.History;
import microservice.microtimes.application.core.domain.SagaEvent;
import microservice.microtimes.application.core.domain.enums.ESagaStatus;
import microservice.microtimes.application.core.domain.value_object.TorneioVo;
import microservice.microtimes.application.port.input.SagaEventSuccessInputPort;
import microservice.microtimes.application.port.output.TimeFindOutputPort;
import microservice.microtimes.application.port.output.*;
import microservice.microtimes.config.exception.http.SagaEventDuplicateException;
import microservice.microtimes.config.exception.http.SagaEventNullValueException;
import microservice.microtimes.config.exception.http_404.TimeNotFoundException;
import microservice.microtimes.config.exception.http_500.NullValueException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import java.time.OffsetDateTime;
import java.util.Optional;

public class SagaEventSuccessUseCase implements SagaEventSuccessInputPort {

    private static final Logger log = LoggerFactory.getLogger(SagaEventSuccessUseCase.class);

    private static final String CURRENT_SOURCE = "TIME-VALIDATION-SUCCESS";

    private final TimeFindOutputPort timeFindOutputPort;

    private final TimeSaveOutputPort timeSaveOutputPort;

    private final SagaEventOrchestratorOutputPort sagaEventOrchestratorOutputPort;

    private final MapperIn mapperIn;

    private final JsonUtil jsonUtil;

    public SagaEventSuccessUseCase(TimeFindOutputPort timeFindOutputPort,
                                   TimeSaveOutputPort timeSaveOutputPort,
                                   SagaEventOrchestratorOutputPort sagaEventOrchestratorOutputPort,
                                   MapperIn mapperIn,
                                   JsonUtil jsonUtil) {
        this.timeFindOutputPort = timeFindOutputPort;
        this.timeSaveOutputPort = timeSaveOutputPort;
        this.sagaEventOrchestratorOutputPort = sagaEventOrchestratorOutputPort;
        this.mapperIn = mapperIn;
        this.jsonUtil = jsonUtil;
    }

    @Override
    public SagaEvent addTorneioInTime(SagaEvent sagaEvent) {

        log.info("Iniciado serviço para inscrever Torneio no Time.");

        var sagaEventConclusion = Optional.ofNullable(sagaEvent)
            .map(this::sagaProcessSuccess)
            .map(this::sagaResponseOrchestrator)
            .orElseThrow();

        log.info("Finalizado serviço para inscrever Torneio no Time. Veja o evento: {}", sagaEventConclusion);

        return sagaEventConclusion;
    }

    private SagaEvent sagaProcessSuccess(SagaEvent event) {
        try {
            this.checkExistenceTimeIdAndTorneioId(event);
            this.addTorneio(event);
            this.handleSuccess(event);

        } catch (SagaEventNullValueException | TimeNotFoundException | SagaEventDuplicateException ex) {
            log.error("Erro: {}", ex.getMessage(), ex);
            this.handleFail(event, ex.getMessage());
        }
        return event;
    }

    private void checkExistenceTimeIdAndTorneioId(SagaEvent event) {
        if (ObjectUtils.isEmpty(event.getTimeId()) || ObjectUtils.isEmpty(event.getTorneioId())) {
            throw new SagaEventNullValueException();
        }
    }

    private void addTorneio(SagaEvent sagaEvent) {

        Optional.ofNullable(sagaEvent)
            .ifPresentOrElse(event -> {
                var torneioVo = new TorneioVo(event.getTorneioId());
                var time = this.timeFindOutputPort.find(event.getTimeId());
                var contain = time.getTorneios().contains(torneioVo);

                if (contain) {
                    throw new SagaEventDuplicateException(event.getTorneioId());
                }

                time.getTorneios().add(torneioVo);
                this.timeSaveOutputPort.save(time);
            },
            () -> {throw new NullValueException();}
        );
    }

    private void handleSuccess(SagaEvent event) {
        event.setStatus(ESagaStatus.SUCCESS);
        event.setSource(CURRENT_SOURCE);
        this.addHistory(event, "Sucesso ao inscrever Torneio no Time.");
    }

    private void addHistory(SagaEvent event, String message) {
        var history = new History();
        history.setMessage(message);
        history.setSource(event.getSource());
        history.setStatus(event.getStatus());
        history.setCreatedAt(OffsetDateTime.now());

        event.addToHistory(history);
    }

    private void handleFail(SagaEvent event, String message) {
        event.setStatus(ESagaStatus.ROLLBACK_PENDING);
        event.setSource(CURRENT_SOURCE);
        this.addHistory(event, "Falha ao inscrever Torneio no Time: ".concat(message));
    }

    private SagaEvent sagaResponseOrchestrator(SagaEvent event) {
        var sagaEventRequest = this.mapperIn.toSagaEventRequest(event);
        var payload = this.jsonUtil.toJson(sagaEventRequest);
        this.sagaEventOrchestratorOutputPort.sendEvent(payload);
        return event;
    }

}

