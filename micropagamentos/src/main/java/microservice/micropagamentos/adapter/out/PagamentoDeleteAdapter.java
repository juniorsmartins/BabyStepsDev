package microservice.micropagamentos.adapter.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.micropagamentos.adapter.out.repository.PagamentoRepository;
import microservice.micropagamentos.application.port.output.PagamentoDeleteOutputPort;
import microservice.micropagamentos.config.exception.http_404.PagamentoNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class PagamentoDeleteAdapter implements PagamentoDeleteOutputPort {

    private final PagamentoRepository pagamentoRepository;

    @Transactional
    @Override
    public void deleteById(final Long id) {

        log.info("Adaptador iniciado para deletar Pagamento.");

        this.pagamentoRepository.findById(id)
            .ifPresentOrElse(this.pagamentoRepository::delete,
                () -> {throw new PagamentoNotFoundException(id);}
            );

        log.info("Adaptador finalizado para deletar Pagamento pelo Id: {}.", id);
    }

}

