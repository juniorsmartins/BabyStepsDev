package microservice.micronoticias.config.exception.http_409;

public final class EditorialInUseException extends RegraDeNegocioVioladaException {

    public EditorialInUseException(String message) {
        super(message);
    }

    public EditorialInUseException(Long id) {
        this(String.format("A Editoria, com Id: %d, está em uso.", id));
    }
}

