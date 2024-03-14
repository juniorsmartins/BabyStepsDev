package microservice.microtorneios.application.core.usecase;

import microservice.microtorneios.adapters.mapper.MapperIn;
import microservice.microtorneios.adapters.utils.JsonUtil;
import microservice.microtorneios.application.core.domain.History;
import microservice.microtorneios.application.core.domain.SagaEvent;
import microservice.microtorneios.application.core.domain.enums.ESagaStatus;
import microservice.microtorneios.application.core.domain.value_object.TimeVo;
import microservice.microtorneios.application.port.input.SagaEventSuccessInputPort;
import microservice.microtorneios.application.port.output.SagaEventOrchestratorOutputPort;
import microservice.microtorneios.application.port.output.TorneioFindOutputPort;
import microservice.microtorneios.application.port.output.TorneioSaveOutputPort;
import microservice.microtorneios.config.exception.http.SagaEventDuplicateException;
import microservice.microtorneios.config.exception.http.SagaEventNullValueException;
import microservice.microtorneios.config.exception.http_404.TorneioNotFoundException;
import microservice.microtorneios.config.exception.http_500.NullValueException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import java.time.OffsetDateTime;
import java.util.Optional;

public class SagaEventSuccessUseCase implements SagaEventSuccessInputPort {

    private static final Logger log = LoggerFactory.getLogger(SagaEventSuccessUseCase.class);

    private static final String CURRENT_SOURCE = "TORNEIO-VALIDATION-SUCCESS";

    private final TorneioFindOutputPort torneioFindOutputPort;

    private final TorneioSaveOutputPort torneioSaveOutputPort;

    private final SagaEventOrchestratorOutputPort sagaEventOrchestratorOutputPort;

    private final MapperIn mapperIn;

    private final JsonUtil jsonUtil;

    public SagaEventSuccessUseCase(MapperIn mapperIn, JsonUtil jsonUtil, TorneioFindOutputPort torneioFindOutputPort,
                                   TorneioSaveOutputPort torneioSaveOutputPort,
                                   SagaEventOrchestratorOutputPort sagaEventOrchestratorOutputPort) {
        this.mapperIn = mapperIn;
        this.jsonUtil = jsonUtil;
        this.torneioFindOutputPort = torneioFindOutputPort;
        this.torneioSaveOutputPort = torneioSaveOutputPort;
        this.sagaEventOrchestratorOutputPort = sagaEventOrchestratorOutputPort;
    }

    @Override
    public SagaEvent addTimeInTorneio(SagaEvent sagaEvent) {

        log.info("Iniciado serviço para inscrever Time no Torneio.");

        var sagaEventConclusion = Optional.ofNullable(sagaEvent)
            .map(this::sagaProcessSuccess)
            .map(this::sagaResponseOrchestrator)
            .orElseThrow();

        log.info("Finalizado serviço para inscrever Time no Torneio. Veja o evento: {}", sagaEventConclusion);

        return sagaEventConclusion;
    }

    private SagaEvent sagaProcessSuccess(SagaEvent event) {
        try {
            this.checkExistenceTimeIdAndTorneioId(event);
            this.addTime(event);
            this.handleSuccess(event);

        } catch (SagaEventNullValueException | TorneioNotFoundException | SagaEventDuplicateException ex) {
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

    private void addTime(SagaEvent sagaEvent) {

        Optional.ofNullable(sagaEvent)
            .ifPresentOrElse(event -> {
                var timeVo = new TimeVo(event.getTimeId());
                var torneio = this.torneioFindOutputPort.find(event.getTorneioId());
                var contain = torneio.getTimes().contains(timeVo);

                if (contain) {
                    throw new SagaEventDuplicateException(event.getTimeId());
                }

                torneio.getTimes().add(timeVo);
                this.torneioSaveOutputPort.save(torneio);
            },
            () -> {throw new NullValueException();}
        );
    }

    private void handleSuccess(SagaEvent event) {
        event.setStatus(ESagaStatus.SUCCESS);
        event.setSource(CURRENT_SOURCE);
        this.addHistory(event, "Sucesso ao inscrever Time no Torneio.");
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
        this.addHistory(event, "Falha ao inscrever Time no Torneio: ".concat(message));
    }

    private SagaEvent sagaResponseOrchestrator(SagaEvent event) {
        var sagaEventRequest = this.mapperIn.toSagaEventRequest(event);
        var payload = this.jsonUtil.toJson(sagaEventRequest);
        this.sagaEventOrchestratorOutputPort.sendEvent(payload);
        return event;
    }

}

