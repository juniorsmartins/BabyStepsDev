package microservice.microtimes.config.exception.http_409;

import java.io.Serial;

public final class SuccessValidationDuplicationException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public SuccessValidationDuplicationException() {
        super("O SagaEvent já existe! Não é permitido salvar Success-Validation duplicado.");
    }
}
