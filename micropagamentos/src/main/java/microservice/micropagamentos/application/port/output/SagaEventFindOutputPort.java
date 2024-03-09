package microservice.micropagamentos.application.port.output;

import microservice.micropagamentos.application.core.domain.Pagamento;

import java.util.Optional;

public interface SagaEventFindOutputPort {

    Optional<Pagamento> findBySagaEventIdAndTransactionId(Long sagaEventId, String transactionId);
}
