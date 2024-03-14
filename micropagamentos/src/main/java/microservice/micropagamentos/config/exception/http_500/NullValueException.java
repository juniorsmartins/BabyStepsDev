package microservice.micropagamentos.config.exception.http_500;

import java.io.Serial;

public final class NullValueException extends InternalServerFailureException {

    @Serial
    private static final long serialVersionUID = 1L;

    public NullValueException() {
        super("exception.null.value");
    }
}

