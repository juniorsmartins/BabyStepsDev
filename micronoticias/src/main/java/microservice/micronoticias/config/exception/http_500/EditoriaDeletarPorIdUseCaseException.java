package microservice.micronoticias.config.exception.http_500;

public final class EditoriaDeletarPorIdUseCaseException extends ProblemaInternoNoServidorException {

    public EditoriaDeletarPorIdUseCaseException(String mensagem) {
        super(mensagem);
    }

    public EditoriaDeletarPorIdUseCaseException() {
        this("Falha ao processar informações na classe, EditoriaDeletarPorIdUseCase.");
    }
}

