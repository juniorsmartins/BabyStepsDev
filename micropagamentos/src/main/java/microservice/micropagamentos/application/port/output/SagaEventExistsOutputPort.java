package microservice.micropagamentos.application.port.output;

public interface SagaEventExistsOutputPort {

    Boolean existsDuplication(Long sagaEventId, String transactionId);
}

