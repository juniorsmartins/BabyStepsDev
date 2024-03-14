package microservice.micropagamentos.config.exception.http_404;

import lombok.Getter;

import java.io.Serial;

@Getter
public abstract sealed class RecursoNotFoundException extends RuntimeException
    permits PagamentoNotFoundException {

    @Serial
    private static final long serialVersionUID = 1L;

    private final Long id;

    private final Long torneioId;

    private final Long timeId;

    private final String messageKey;

    protected RecursoNotFoundException(final String messageKey, final Long id) {
        super(messageKey);
        this.messageKey = messageKey;
        this.id = id;
        this.torneioId = 0L;
        this.timeId = 0L;
    }

    protected RecursoNotFoundException(final String messageKey, final Long torneioId, final Long timeId) {
        super(messageKey);
        this.messageKey = messageKey;
        this.torneioId = torneioId;
        this.timeId = timeId;
        this.id = 0L;
    }
}

