package microservice.microtorneios.config.exception.http_500;

import lombok.Getter;

import java.io.Serial;

@Getter
public abstract sealed class InternalServerFailureException extends RuntimeException permits
    CarteiroFailSendLetterException {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String messageKey;

    protected InternalServerFailureException(String messageKey) {
        super(messageKey);
        this.messageKey = messageKey;
    }
}

