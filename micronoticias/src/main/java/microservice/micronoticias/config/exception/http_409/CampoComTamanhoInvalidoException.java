package microservice.micronoticias.config.exception.http_409;

public final class CampoComTamanhoInvalidoException extends RegraDeNegocioVioladaException {

    public CampoComTamanhoInvalidoException(String nomeCampo, int limiteMinino, int limiteMaximo, int caracteresEnviados) {
        super(String.format("O campo %s possui limites mínimo de %d e máximo de %d caracteres, mas enviaste %d.",
                nomeCampo, limiteMinino, limiteMaximo, caracteresEnviados));
    }
}

