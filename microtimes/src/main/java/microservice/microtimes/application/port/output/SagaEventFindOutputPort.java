package microservice.microtimes.application.port.output;

import microservice.microtimes.application.core.domain.ValidationModel;

import java.util.Optional;

public interface SagaEventFindOutputPort {

    Optional<ValidationModel> findBySagaEventIdAndTransactionId(Long sagaEventId, String transactionId);
}
