package microservice.micronoticias.config.exception.http_500;

public final class NoticiaUpdateUseCaseException extends ProblemaInternoNoServidorException {

    public NoticiaUpdateUseCaseException(String mensagem) {
        super(mensagem);
    }

    public NoticiaUpdateUseCaseException() {
        this("Falha ao processar informações na classe, NoticiaUpdateUseCase.");
    }
}

