package microservice.micropagamentos.adapter.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.micropagamentos.adapter.out.repository.PagamentoRepository;
import microservice.micropagamentos.application.port.output.SagaEventExistsOutputPort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class SagaEventExistsAdapter implements SagaEventExistsOutputPort {

    private final PagamentoRepository pagamentoRepository;

    @Transactional(readOnly = true)
    @Override
    public Boolean existsDuplication(final Long sagaEventId, final String transactionId) {
        return this.pagamentoRepository.existsBySagaEventIdAndTransactionId(sagaEventId, transactionId);
    }
}

