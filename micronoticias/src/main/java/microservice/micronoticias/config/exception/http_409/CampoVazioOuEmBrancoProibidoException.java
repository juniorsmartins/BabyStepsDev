package microservice.micronoticias.config.exception.http_409;

public final class CampoVazioOuEmBrancoProibidoException extends RegraDeNegocioVioladaException {

    public CampoVazioOuEmBrancoProibidoException(String nomeCampo) {
        super(String.format("O campo %s não pode ser vazio ou em branco.", nomeCampo));
    }
}

