package microservice.micronoticias.config.exception.http_409;

import java.io.Serial;

public abstract sealed class RegraDeNegocioVioladaException extends RuntimeException
        permits CampoVazioOuEmBrancoProibidoException, CampoNuloProibidoException, CampoComTamanhoInvalidoException,
        NomenclaturaNaoUnicaException {

    @Serial
    private static final long serialVersionUID = 1L;

    RegraDeNegocioVioladaException(String mensagem) {
        super(mensagem);
    }
}

