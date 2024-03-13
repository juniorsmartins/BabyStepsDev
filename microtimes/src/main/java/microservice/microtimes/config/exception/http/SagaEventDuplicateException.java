package microservice.microtimes.config.exception.http;

import java.io.Serial;

public final class SagaEventDuplicateException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public SagaEventDuplicateException(final Long id) {
        super(String.format( "O SagaEvent cometeu duplicação ao tentar inscrever Torneio, com Id: %d, pela segunda vez no mesmo Time.", id));
    }
}

