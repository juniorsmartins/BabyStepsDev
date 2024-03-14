package microservice.micropagamentos.adapter.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.micropagamentos.adapter.mapper.MapperOut;
import microservice.micropagamentos.adapter.out.repository.PagamentoRepository;
import microservice.micropagamentos.application.core.domain.Pagamento;
import microservice.micropagamentos.application.port.output.PagamentoFindOutputPort;
import microservice.micropagamentos.config.exception.http_404.PagamentoNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class PagamentoFindAdapter implements PagamentoFindOutputPort {

    private final PagamentoRepository pagamentoRepository;

    private final MapperOut mapperOut;

    @Transactional(readOnly = true)
    @Override
    public Pagamento findByTorneioIdAndTimeId(final Long torneioId, final Long timeId) {

        log.info("Adaptador iniciado para buscar Pagamento.");

        var pagamentoEncontrado = this.pagamentoRepository.findByTorneioIdAndTimeId(torneioId, timeId)
            .map(this.mapperOut::toPagamento)
            .orElseThrow(() -> new PagamentoNotFoundException(torneioId, timeId));

        log.info("Adaptador finalizado para buscar Pagamento: {}.", pagamentoEncontrado);

        return pagamentoEncontrado;
    }
}

