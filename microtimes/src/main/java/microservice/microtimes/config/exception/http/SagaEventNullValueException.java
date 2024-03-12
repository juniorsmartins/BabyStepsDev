package microservice.microtimes.config.exception.http;

import java.io.Serial;

public final class SagaEventNullValueException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public SagaEventNullValueException() {
        super("O SagaEvent possui campo com valor nulo proibido.");
    }
}
