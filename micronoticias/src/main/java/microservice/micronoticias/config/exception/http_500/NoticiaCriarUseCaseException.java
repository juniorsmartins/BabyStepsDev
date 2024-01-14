package microservice.micronoticias.config.exception.http_500;

public final class NoticiaCriarUseCaseException extends ProblemaInternoNoServidorException {

    public NoticiaCriarUseCaseException(String mensagem) {
        super(mensagem);
    }

    public NoticiaCriarUseCaseException() {
        this("Falha ao processar informações na classe, NoticiaCriarUseCase.");
    }
}

