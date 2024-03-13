package microservice.microtimes.config.exception.http_404;

import java.io.Serial;

public final class TorneioNotFoundException extends RecursoNotFoundException {

    @Serial
    private static final long serialVersionUID = 1L;

    public TorneioNotFoundException(final Long id) {
        super("exception.resource.not.found.torneio", id);
    }
}

