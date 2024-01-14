package microservice.micronoticias.config.exception.http_500;

public final class EditoriaSalvarAdapterException extends ProblemaInternoNoServidorException {

    public EditoriaSalvarAdapterException(String mensagem) {
        super(mensagem);
    }

    public EditoriaSalvarAdapterException() {
        this("Falha na transação de salvamento na classe, EditoriaSalvarAdapter.");
    }
}

