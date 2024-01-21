package microservice.micronoticias.config.exception.http_500;

public final class FailedToUpdateEditorException extends ProblemaInternoNoServidorException {

    public FailedToUpdateEditorException(String mensagem) {
        super(mensagem);
    }

    public FailedToUpdateEditorException() {
        this("Falha ao atualizar Editoria no banco de dados.");
    }
}

