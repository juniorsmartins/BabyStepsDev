package microservice.micronoticias.config.exception.http_500;

public final class FailedToUpdateNewsException extends ProblemaInternoNoServidorException {

    public FailedToUpdateNewsException(String mensagem) {
        super(mensagem);
    }

    public FailedToUpdateNewsException() {
        this("Falha ao atualizar Notícia no banco de dados.");
    }
}

