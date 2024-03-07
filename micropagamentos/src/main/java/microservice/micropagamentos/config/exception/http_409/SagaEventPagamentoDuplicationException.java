package microservice.micropagamentos.config.exception.http_409;

import java.io.Serial;

public final class SagaEventPagamentoDuplicationException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public SagaEventPagamentoDuplicationException() {
        super("O SagaEvent já existe! Não é permitido salvar Pagamento-Service duplicado.");
    }
}
