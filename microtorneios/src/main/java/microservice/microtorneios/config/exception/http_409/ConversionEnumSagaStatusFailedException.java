package microservice.microtorneios.config.exception.http_409;

import java.io.Serial;

public final class ConversionEnumSagaStatusFailedException extends BusinessRuleViolationException {

    @Serial
    private static final long serialVersionUID = 1L;

    public ConversionEnumSagaStatusFailedException() {
        super("enum.saga.status.failed");
    }
}
