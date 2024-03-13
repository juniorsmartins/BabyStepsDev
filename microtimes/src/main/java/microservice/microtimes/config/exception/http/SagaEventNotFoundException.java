package microservice.microtimes.config.exception.http;

import java.io.Serial;

public final class SagaEventNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public SagaEventNotFoundException(final Long id, final String recurso) {
        super(String.format("Não encontrado Id, %d, do recurso: %s", id, recurso));
    }
}

