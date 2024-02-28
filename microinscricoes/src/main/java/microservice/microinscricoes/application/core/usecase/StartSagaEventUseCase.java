package microservice.microinscricoes.application.core.usecase;

import microservice.microinscricoes.application.core.domain.Inscrito;
import microservice.microinscricoes.application.core.domain.Order;
import microservice.microinscricoes.application.core.domain.SagaEvent;
import microservice.microinscricoes.application.core.domain.value_object.History;
import microservice.microinscricoes.application.port.StartSagaEventPort;
import microservice.microinscricoes.application.port.output.OrderSaveOutputPort;
import microservice.microinscricoes.application.port.output.SagaEventSaveOutputPort;
import microservice.microinscricoes.application.port.output.StartSagaProducerOutputPort;

import java.util.List;
import java.util.Optional;

public class StartSagaEventUseCase implements StartSagaEventPort {

    private final SagaEventSaveOutputPort sagaEventSaveOutputPort;

    private final OrderSaveOutputPort orderSaveOutputPort;

    private final StartSagaProducerOutputPort startSagaProducerOutputPort;

    public StartSagaEventUseCase(SagaEventSaveOutputPort sagaEventSaveOutputPort,
                                 OrderSaveOutputPort orderSaveOutputPort,
                                 StartSagaProducerOutputPort startSagaProducerOutputPort) {
        this.sagaEventSaveOutputPort = sagaEventSaveOutputPort;
        this.orderSaveOutputPort = orderSaveOutputPort;
        this.startSagaProducerOutputPort = startSagaProducerOutputPort;
    }

    @Override
    public void sendEvent(Inscrito inscrito) {

        Optional.ofNullable(inscrito)
            .map(this::createSagaEvent)
            .map(this::saveOrder)
            .map(this.sagaEventSaveOutputPort::save)
            .map(this::send)
            .orElseThrow();
    }

    private SagaEvent createSagaEvent(Inscrito inscrito) {

        var sagaEvent = new SagaEvent();
//        sagaEvent.setTransactionId();
        sagaEvent.setInscricaoId(inscrito.getInscricao().getId());
        sagaEvent.setInscritoId(inscrito.getId());
        sagaEvent.setTorneioId(inscrito.getInscricao().getTorneio().getId());
        sagaEvent.setTimeId(inscrito.getTime().getId());
        sagaEvent.setPayload(new Order(inscrito));
//        sagaEvent.setSource();
//        sagaEvent.setStatus();
        sagaEvent.setEventHistories(List.of(new History()));

        return sagaEvent;
    }

    private SagaEvent saveOrder(SagaEvent sagaEvent) {
        var orderSaved = this.orderSaveOutputPort.save(sagaEvent.getPayload());
        sagaEvent.setPayload(orderSaved);
        return sagaEvent;
    }

    private SagaEvent send(SagaEvent sagaEvent) {
        this.startSagaProducerOutputPort.sendEvent(sagaEvent);
        return sagaEvent;
    }
}

