package microservice.micronoticias.config.exception.http_409;

public final class CampoNuloProibidoException extends RegraDeNegocioVioladaException {

    public CampoNuloProibidoException(String nomeCampo) {
        super(String.format("O campo %s não pode ser nulo.", nomeCampo));
    }
}

