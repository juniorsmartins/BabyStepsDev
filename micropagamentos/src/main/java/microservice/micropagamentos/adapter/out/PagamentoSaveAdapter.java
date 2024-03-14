package microservice.micropagamentos.adapter.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.micropagamentos.adapter.mapper.MapperOut;
import microservice.micropagamentos.adapter.out.repository.PagamentoRepository;
import microservice.micropagamentos.application.core.domain.Pagamento;
import microservice.micropagamentos.application.port.output.PagamentoSaveOutputPort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class PagamentoSaveAdapter implements PagamentoSaveOutputPort {

    private final PagamentoRepository pagamentoRepository;

    private final MapperOut mapperOut;

    @Transactional
    @Override
    public Pagamento save(Pagamento pagamento) {

        log.info("Adaptador iniciado para salvar Pagamento.");

        var pagamentoSaved = Optional.ofNullable(pagamento)
            .map(this.mapperOut::toPagamentoEntity)
            .map(this.pagamentoRepository::save)
            .map(this.mapperOut::toPagamento)
            .orElseThrow();

        log.info("Adaptador finalizado para salvar Pagamento: {}.", pagamentoSaved);

        return pagamentoSaved;
    }

}

