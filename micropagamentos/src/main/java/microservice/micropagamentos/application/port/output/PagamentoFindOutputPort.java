package microservice.micropagamentos.application.port.output;

import microservice.micropagamentos.application.core.domain.Pagamento;

public interface PagamentoFindOutputPort {

    Pagamento findByTorneioIdAndTimeId(Long torneioId, Long timeId);

}

