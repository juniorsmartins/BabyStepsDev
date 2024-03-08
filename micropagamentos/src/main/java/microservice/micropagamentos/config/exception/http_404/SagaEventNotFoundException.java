package microservice.micropagamentos.config.exception.http_404;

import java.io.Serial;

public final class SagaEventNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public SagaEventNotFoundException() {
        super("O SagaEvent não foi encontrado.");
    }
}
