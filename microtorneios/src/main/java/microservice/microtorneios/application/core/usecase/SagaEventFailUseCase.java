package microservice.microtorneios.application.core.usecase;

import microservice.microtorneios.adapters.mapper.MapperIn;
import microservice.microtorneios.adapters.utils.JsonUtil;
import microservice.microtorneios.application.core.domain.History;
import microservice.microtorneios.application.core.domain.SagaEvent;
import microservice.microtorneios.application.core.domain.enums.ESagaStatus;
import microservice.microtorneios.application.core.domain.value_object.TimeVo;
import microservice.microtorneios.application.port.input.SagaEventFailInputPort;
import microservice.microtorneios.application.port.output.CarteiroNotifyOrchestratorOutputPort;
import microservice.microtorneios.application.port.output.TorneioFindOutputPort;
import microservice.microtorneios.application.port.output.TorneioSaveOutputPort;
import microservice.microtorneios.config.exception.http.SagaEventNotFoundException;
import microservice.microtorneios.config.exception.http.SagaEventNullValueException;
import microservice.microtorneios.config.exception.http_404.TorneioNotFoundException;
import microservice.microtorneios.config.exception.http_500.NullValueException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import java.time.OffsetDateTime;
import java.util.Optional;

public class SagaEventFailUseCase implements SagaEventFailInputPort {

    private static final Logger log = LoggerFactory.getLogger(SagaEventFailUseCase.class);

    private static final String CURRENT_SOURCE = "TORNEIO_SERVICE";

    private final TorneioFindOutputPort torneioFindOutputPort;

    private final TorneioSaveOutputPort torneioSaveOutputPort;

    private final CarteiroNotifyOrchestratorOutputPort sagaEventOrchestratorOutputPort;

    private final MapperIn mapperIn;

    private final JsonUtil jsonUtil;

    public SagaEventFailUseCase(MapperIn mapperIn, JsonUtil jsonUtil, TorneioFindOutputPort torneioFindOutputPort,
                                TorneioSaveOutputPort torneioSaveOutputPort,
                                CarteiroNotifyOrchestratorOutputPort sagaEventOrchestratorOutputPort) {
        this.mapperIn = mapperIn;
        this.jsonUtil = jsonUtil;
        this.torneioFindOutputPort = torneioFindOutputPort;
        this.torneioSaveOutputPort = torneioSaveOutputPort;
        this.sagaEventOrchestratorOutputPort = sagaEventOrchestratorOutputPort;
    }

    @Override
    public SagaEvent rollbackEvent(SagaEvent event) {

        log.info("Iniciado serviço para remover Time do Torneio.");

        var sagaEventConclusion = Optional.ofNullable(event)
            .map(this::sagaProcessFail)
            .map(this::sagaResponseOrchestrator)
            .orElseThrow();

        log.info("Finalizado serviço para remover Time do Torneio. Veja o evento: {}", sagaEventConclusion);

        return sagaEventConclusion;
    }

    private SagaEvent sagaProcessFail(SagaEvent event) {
        try {
            this.checkExistenceTimeIdAndTorneioId(event);
            this.removeTime(event);
            this.handleSuccessFail(event);

        } catch (SagaEventNullValueException | TorneioNotFoundException | SagaEventNotFoundException ex) {
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

    private void removeTime(SagaEvent sagaEvent) {

        Optional.ofNullable(sagaEvent)
            .ifPresentOrElse(event -> {
                var timeVo = new TimeVo(event.getTimeId());
                var torneio = this.torneioFindOutputPort.find(event.getTorneioId());
                var contain = torneio.getTimes().contains(timeVo);

                if (!contain) {
                    throw new SagaEventNotFoundException(event.getTimeId(), "Time");
                }

                torneio.getTimes().remove(timeVo);
                this.torneioSaveOutputPort.save(torneio);
            },
            () -> {throw new NullValueException();}
        );
    }

    private void handleSuccessFail(SagaEvent event) {
        event.setStatus(ESagaStatus.FAIL);
        event.setSource(CURRENT_SOURCE);
        this.addHistory(event, "Rollback bem-sucedido ao remover Time do Torneio.");
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
        this.addHistory(event, "Rollback falha ao remover Time do Torneio: ".concat(message));
    }

    private SagaEvent sagaResponseOrchestrator(SagaEvent event) {
        var sagaEventRequest = this.mapperIn.toSagaEventRequest(event);
        var payload = this.jsonUtil.toJson(sagaEventRequest);
        this.sagaEventOrchestratorOutputPort.sendEvent(payload);
        return event;
    }

}

