package microservice.orchestrator.config.exception.http_500;

import java.io.Serial;

public final class CarteiroFailSendLetterException extends InternalServerFailureException {

    @Serial
    private static final long serialVersionUID = 1L;

    public CarteiroFailSendLetterException() {
        super("exception.carteiro.fail.send.create.torneio");
    }
}

