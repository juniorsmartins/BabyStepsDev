package microservice.microinscricoes.config.exception.http_409;

import java.io.Serial;

public final class FiltersEventEmptyException extends BusinessRuleViolationException {

    @Serial
    private static final long serialVersionUID = 1L;

    public FiltersEventEmptyException() {
        super("filters.event.empty");
    }
}
