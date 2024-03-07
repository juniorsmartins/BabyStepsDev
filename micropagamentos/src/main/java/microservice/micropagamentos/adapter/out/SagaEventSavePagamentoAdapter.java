package microservice.micropagamentos.adapter.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.micropagamentos.adapter.mapper.MapperOut;
import microservice.micropagamentos.adapter.out.repository.PagamentoRepository;
import microservice.micropagamentos.application.core.domain.Pagamento;
import microservice.micropagamentos.application.port.output.SagaEventSavePagamentoOutputPort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class SagaEventSavePagamentoAdapter implements SagaEventSavePagamentoOutputPort {

    private final PagamentoRepository pagamentoRepository;

    private final MapperOut mapperOut;

    @Transactional
    @Override
    public Pagamento save(Pagamento pagamento) {

        log.info("Iniciado adaptador para salvar Pagamento-Service.");

        var pagamentoSaved = Optional.ofNullable(pagamento)
            .map(this.mapperOut::toPagamentoEntity)
            .map(this.pagamentoRepository::save)
            .map(this.mapperOut::toPagamento)
            .orElseThrow();

        log.info("Finalizado adaptador para salvar Pagamento-Service: {}.", pagamentoSaved);

        return pagamentoSaved;
    }
}

