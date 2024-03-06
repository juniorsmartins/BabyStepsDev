package microservice.microtimes.config.exception.http_409;

import java.io.Serial;

public final class SuccessValidationDuplicationException extends BusinessRuleViolationException {

    @Serial
    private static final long serialVersionUID = 1L;

    public SuccessValidationDuplicationException() {
        super("exception.business.rule.violation.success.validation.duplication");
    }
}
