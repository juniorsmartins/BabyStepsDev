package microservice.micronoticias.config.exception.http_500;

public final class ProhibitedNullValueException extends ProblemaInternoNoServidorException {

    public ProhibitedNullValueException(String mensagem) {
        super(mensagem);
    }

    public ProhibitedNullValueException() {
        this("Valor nulo proibido.");
    }
}

