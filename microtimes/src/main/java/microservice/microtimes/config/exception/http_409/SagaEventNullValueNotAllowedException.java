package microservice.microtimes.config.exception.http_409;

import java.io.Serial;

public final class SagaEventNullValueNotAllowedException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public SagaEventNullValueNotAllowedException() {
        super("O SagaEvent possui campo com valor nulo proibido.");
    }
}
