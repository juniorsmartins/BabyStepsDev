package microservice.microtimes.config.exception.http;

import java.io.Serial;

public final class SagaEventDuplicationException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public SagaEventDuplicationException() {
        super("O SagaEvent já existe! Não é permitido salvar Success-Validation duplicado.");
    }
}
