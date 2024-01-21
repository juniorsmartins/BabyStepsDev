package microservice.micronoticias.config.exception.http_500;

public final class EditoriaUpdateUseCaseException extends ProblemaInternoNoServidorException {

    public EditoriaUpdateUseCaseException(String mensagem) {
        super(mensagem);
    }

    public EditoriaUpdateUseCaseException() {
        this("Falha no serviço de atualizar Editoria.");
    }
}

