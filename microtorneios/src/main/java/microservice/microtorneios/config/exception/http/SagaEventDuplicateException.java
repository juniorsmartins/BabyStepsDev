package microservice.microtorneios.config.exception.http;

import java.io.Serial;

public final class SagaEventDuplicateException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public SagaEventDuplicateException(final Long id) {
        super(String.format( "O SagaEvent tentou inscrever Time, com Id: %d, já inscrito no Torneio.", id));
    }
}

