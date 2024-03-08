package microservice.micropagamentos.adapter.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.micropagamentos.adapter.mapper.MapperOut;
import microservice.micropagamentos.adapter.out.repository.PagamentoRepository;
import microservice.micropagamentos.application.core.domain.Pagamento;
import microservice.micropagamentos.application.port.output.SagaEventFindOutputPort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class SagaEventFindAdapter implements SagaEventFindOutputPort {

    private final PagamentoRepository pagamentoRepository;

    private final MapperOut mapperOut;

    @Transactional(readOnly = true)
    @Override
    public Optional<Pagamento> findBySagaEventIdAndTransactionId(final Long sagaEventId, final String transactionId) {

        return this.pagamentoRepository.findBySagaEventIdAndTransactionId(sagaEventId, transactionId)
            .map(this.mapperOut::toPagamento);
    }
}

