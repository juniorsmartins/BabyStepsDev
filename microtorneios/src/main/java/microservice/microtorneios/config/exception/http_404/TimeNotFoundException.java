package microservice.microtorneios.config.exception.http_404;

import java.io.Serial;

public final class TimeNotFoundException extends RecursoNotFoundException {

    @Serial
    private static final long serialVersionUID = 1L;

    public TimeNotFoundException(final Long id) {
        super("exception.resource.not.found.time", id);
    }
}

