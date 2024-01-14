package microservice.micronoticias.config.exception.http_500;

public final class EditoriaCriarUseCaseException extends ProblemaInternoNoServidorException {

    public EditoriaCriarUseCaseException(String mensagem) {
        super(mensagem);
    }

    public EditoriaCriarUseCaseException() {
        this("Falha ao processar informações na classe, EditoriaCriarUseCase.");
    }
}

