package microservice.microtimes.application.port.output;

public interface SagaEventExistsOutputPort {

    Boolean existsDuplication(Long sagaEventId, String transactionId);
}

