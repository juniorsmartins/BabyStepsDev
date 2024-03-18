package microservice.microtimes.application.core.usecase;

import microservice.microtimes.adapter.mapper.MapperIn;
import microservice.microtimes.adapter.utils.JsonUtil;
import microservice.microtimes.application.core.domain.History;
import microservice.microtimes.application.core.domain.SagaEvent;
import microservice.microtimes.application.core.domain.enums.ESagaStatus;
import microservice.microtimes.application.core.domain.value_object.TorneioVo;
import microservice.microtimes.application.port.input.SagaEventFailInputPort;
import microservice.microtimes.application.port.output.TimeFindOutputPort;
import microservice.microtimes.application.port.output.CarteiroNotifyOrchestratorOutputPort;
import microservice.microtimes.application.port.output.TimeSaveOutputPort;
import microservice.microtimes.config.exception.http.SagaEventNotFoundException;
import microservice.microtimes.config.exception.http.SagaEventNullValueException;
import microservice.microtimes.config.exception.http_404.TimeNotFoundException;
import microservice.microtimes.config.exception.http_500.NullValueException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import java.time.OffsetDateTime;
import java.util.Optional;

public class SagaEventFailUseCase implements SagaEventFailInputPort {

    private static final Logger log = LoggerFactory.getLogger(SagaEventFailUseCase.class);

    private static final String CURRENT_SOURCE = "TIME_VALIDATION_SERVICE";

    private final TimeFindOutputPort timeFindOutputPort;

    private final TimeSaveOutputPort timeSaveOutputPort;

    private final CarteiroNotifyOrchestratorOutputPort sagaEventOrchestratorOutputPort;

    private final MapperIn mapperIn;

    private final JsonUtil jsonUtil;

    public SagaEventFailUseCase(
            TimeFindOutputPort timeFindOutputPort,
            TimeSaveOutputPort timeSaveOutputPort,
            CarteiroNotifyOrchestratorOutputPort sagaEventOrchestratorOutputPort,
            MapperIn mapperIn,
            JsonUtil jsonUtil
    ) {
        this.timeFindOutputPort = timeFindOutputPort;
        this.timeSaveOutputPort = timeSaveOutputPort;
        this.sagaEventOrchestratorOutputPort = sagaEventOrchestratorOutputPort;
        this.mapperIn = mapperIn;
        this.jsonUtil = jsonUtil;
    }

    @Override
    public SagaEvent rollbackEvent(SagaEvent event) {

        log.info("Serviço iniciado para remover Torneio do Time.");

        var sagaEventConclusion = Optional.ofNullable(event)
            .map(this::sagaProcessFail)
            .map(this::sagaResponseOrchestrator)
            .orElseThrow();

        log.info("Serviço finalizado para remover Torneio do Time. Veja o evento: {}", sagaEventConclusion);

        return sagaEventConclusion;
    }

    private SagaEvent sagaProcessFail(SagaEvent event) {
        try {
            this.checkExistenceTimeIdAndTorneioId(event);
            this.removeTorneio(event);
            this.handleSuccessFail(event);

        } catch (SagaEventNullValueException | TimeNotFoundException | SagaEventNotFoundException ex) {
            log.error("Erro: {}", ex.getMessage(), ex);
            this.handleRollbackPending(event, ex.getMessage());
        }
        return event;
    }

    private void checkExistenceTimeIdAndTorneioId(SagaEvent event) {
        if (ObjectUtils.isEmpty(event.getTimeId()) || ObjectUtils.isEmpty(event.getTorneioId())) {
            throw new SagaEventNullValueException();
        }
    }

    private void removeTorneio(SagaEvent sagaEvent) {

        Optional.ofNullable(sagaEvent)
            .ifPresentOrElse(event -> {
                var torneioVo = new TorneioVo(event.getTorneioId());
                var time = this.timeFindOutputPort.find(event.getTimeId());
                var contain = time.getTorneios().contains(torneioVo);

                if (!contain) {
                    throw new SagaEventNotFoundException(event.getTorneioId(), "Torneio");
                }

                time.getTorneios().remove(torneioVo);
                this.timeSaveOutputPort.save(time);
            },
            () -> {throw new NullValueException();}
        );
    }

    private void handleSuccessFail(SagaEvent event) {
        event.setStatus(ESagaStatus.FAIL);
        event.setSource(CURRENT_SOURCE);
        this.addHistory(event, "Rollback bem-sucedido ao remover Torneio do Time.");
    }

    private void addHistory(SagaEvent event, String message) {
        var history = new History();
        history.setMessage(message);
        history.setSource(event.getSource());
        history.setStatus(event.getStatus());
        history.setCreatedAt(OffsetDateTime.now());

        event.addToHistory(history);
    }

    private void handleRollbackPending(SagaEvent event, String message) {
        event.setStatus(ESagaStatus.ROLLBACK_PENDING);
        event.setSource(CURRENT_SOURCE);
        this.addHistory(event, "Rollback falha ao remover Torneio do Time: ".concat(message));
    }

    private SagaEvent sagaResponseOrchestrator(SagaEvent event) {
        var sagaEventRequest = this.mapperIn.toSagaEventRequest(event);
        var payload = this.jsonUtil.toJson(sagaEventRequest);
        this.sagaEventOrchestratorOutputPort.sendEvent(payload);
        return event;
    }

}

