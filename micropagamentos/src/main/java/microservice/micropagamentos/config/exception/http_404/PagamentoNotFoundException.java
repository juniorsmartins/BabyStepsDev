package microservice.micropagamentos.config.exception.http_404;

import java.io.Serial;

public final class PagamentoNotFoundException extends RecursoNotFoundException {

    @Serial
    private static final long serialVersionUID = 1L;

    public PagamentoNotFoundException(final Long id) {
        super("exception.resource.not.found.pagamento", id);
    }

    public PagamentoNotFoundException(final Long torneioId, final Long timeId) {
        super("exception.resource.not.found.pagamento.por.ids", torneioId, timeId);
    }
}

