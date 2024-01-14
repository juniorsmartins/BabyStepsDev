package microservice.micronoticias.config.exception.http_500;

public final class NoticiaSalvarAdapterException extends ProblemaInternoNoServidorException {

    public NoticiaSalvarAdapterException(String mensagem) {
        super(mensagem);
    }

    public NoticiaSalvarAdapterException() {
        this("Falha na transação de salvamento na classe, NoticiaSalvarAdapter.");
    }
}

