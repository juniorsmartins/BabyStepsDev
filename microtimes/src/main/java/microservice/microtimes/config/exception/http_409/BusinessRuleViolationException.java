package microservice.microtimes.config.exception.http_409;

import lombok.Getter;

import java.io.Serial;

@Getter
public abstract sealed class BusinessRuleViolationException extends RuntimeException permits
        ConversionEnumSagaStatusFailedException, SuccessValidationDuplicationException {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String messageKey;

    protected BusinessRuleViolationException(String messageKey) {
        super(messageKey);
        this.messageKey = messageKey;
    }
}

