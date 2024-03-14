package microservice.micropagamentos.config.exception.http;

import java.io.Serial;

public final class SagaEventDuplicateException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public SagaEventDuplicateException(final Long torneioId, final Long timeId) {
        super(String.format( "O Pagamento do Time, ID: %d, para se inscrever no Torneio, ID: %d, já foi efetuado! Não pode ser feito pela segunda vez.", timeId, torneioId));
    }
}

