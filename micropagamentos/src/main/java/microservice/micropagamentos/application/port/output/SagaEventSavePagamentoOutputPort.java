package microservice.micropagamentos.application.port.output;

import microservice.micropagamentos.application.core.domain.Pagamento;

public interface SagaEventSavePagamentoOutputPort {

    Pagamento save(Pagamento pagamento);
}

