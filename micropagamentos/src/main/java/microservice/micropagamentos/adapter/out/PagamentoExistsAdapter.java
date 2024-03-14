package microservice.micropagamentos.adapter.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.micropagamentos.adapter.out.repository.PagamentoRepository;
import microservice.micropagamentos.application.port.output.PagamentoExistsOutputPort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class PagamentoExistsAdapter implements PagamentoExistsOutputPort {

    private final PagamentoRepository pagamentoRepository;

    @Transactional(readOnly = true)
    @Override
    public Boolean existsDuplicate(final Long torneioId, final Long timeId) {
        return this.pagamentoRepository.existsByTorneioIdAndTimeId(torneioId, timeId);
    }

}

