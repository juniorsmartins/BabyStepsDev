package microservice.micronoticias.config.exception.http_500;

public final class EditoriaMapperOutImplException extends ProblemaInternoNoServidorException {

    public EditoriaMapperOutImplException(String mensagem) {
        super(mensagem);
    }

    public EditoriaMapperOutImplException() {
        this("Falha ao realizar o procedimento de conversão na classe, EditoriaMapperOutImpl.");
    }
}

