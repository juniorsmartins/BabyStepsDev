package microservice.microtorneios.config.exception.http_404;

import lombok.Getter;

import java.io.Serial;

@Getter
public abstract sealed class RecursoNotFoundException extends RuntimeException permits TimeNotFoundException,
    TorneioNotFoundException {

    @Serial
    private static final long serialVersionUID = 1L;

    private final Long id;

    private final String messageKey;

    protected RecursoNotFoundException(final String messageKey, final Long id) {
        super(messageKey);
        this.messageKey = messageKey;
        this.id = id;
    }
}

