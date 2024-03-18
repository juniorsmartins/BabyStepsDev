package microservice.microinscricoes.application.core.usecase;

import microservice.microinscricoes.application.core.domain.Inscrito;
import microservice.microinscricoes.application.core.domain.SagaEvent;
import microservice.microinscricoes.application.core.domain.enums.EEventSource;
import microservice.microinscricoes.application.core.domain.enums.ESagaStatus;
import microservice.microinscricoes.application.core.domain.value_object.History;
import microservice.microinscricoes.application.port.StartSagaEventPort;
import microservice.microinscricoes.application.port.output.SagaEventSaveOutputPort;
import microservice.microinscricoes.application.port.output.CarteiroNotifyStartOutputPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class StartSagaEventUseCase implements StartSagaEventPort {

    private static final Logger log = LoggerFactory.getLogger(StartSagaEventUseCase.class);

    private final SagaEventSaveOutputPort sagaEventSaveOutputPort;

    private final CarteiroNotifyStartOutputPort startSagaProducerOutputPort;

    public StartSagaEventUseCase(SagaEventSaveOutputPort sagaEventSaveOutputPort,
                                 CarteiroNotifyStartOutputPort startSagaProducerOutputPort) {
        this.sagaEventSaveOutputPort = sagaEventSaveOutputPort;
        this.startSagaProducerOutputPort = startSagaProducerOutputPort;
    }

    @Override
    public void sendEvent(Inscrito inscrito) {

        log.info("Iniciado serviço para enviar Inscrito pela Saga.");

        Optional.ofNullable(inscrito)
            .map(this::createSagaEvent)
            .map(this.sagaEventSaveOutputPort::save)
            .map(this::send)
            .orElseThrow();

        log.info("Finalizado serviço para enviar Inscrito {} pela Saga.", inscrito);
    }

    private SagaEvent createSagaEvent(Inscrito inscrito) {

        var sagaEvent = new SagaEvent();
        sagaEvent.generateTransactionId();
        sagaEvent.setInscricaoId(inscrito.getInscricao().getId());
        sagaEvent.setInscritoId(inscrito.getId());
        sagaEvent.setTorneioId(inscrito.getInscricao().getTorneio().getId());
        sagaEvent.setTimeId(inscrito.getTime().getId());
        sagaEvent.setPayload(inscrito);
        sagaEvent.setSource(EEventSource.INSCRITO_SERVICE.getValue());
        sagaEvent.setStatus(ESagaStatus.SUCCESS.getValue());
        sagaEvent.setEventHistories(List.of(new History()));

        return sagaEvent;
    }

    private SagaEvent send(SagaEvent sagaEvent) {
        this.startSagaProducerOutputPort.sendEvent(sagaEvent);
        return sagaEvent;
    }
}

