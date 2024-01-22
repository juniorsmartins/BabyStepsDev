package microservice.micronoticias.config.exception.http_500;

public final class NoticiaDeleteUseCaseException extends ProblemaInternoNoServidorException {

    public NoticiaDeleteUseCaseException(String mensagem) {
        super(mensagem);
    }

    public NoticiaDeleteUseCaseException() {
        this("Falha ao processar informações na classe, NoticiaDeleteUseCase.");
    }
}

