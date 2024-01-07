package microservice.micronoticias.application.core.useful;

import microservice.micronoticias.config.exception.http_409.CampoComTamanhoInvalidoException;
import microservice.micronoticias.config.exception.http_409.CampoVazioOuEmBrancoProibidoException;

public final class UtilityDomain {

    private UtilityDomain() { }

    public static void validarCampoNaoVazioOuEmBranco(String nomeCampo, String valorCampo) {
        if(valorCampo.isBlank())
            throw new CampoVazioOuEmBrancoProibidoException(nomeCampo);
    }

    public static void validarCampoComTamanhoValido(String nomeCampo, int limiteMinino, int limiteMaximo, int caracteresEnviados) {
        if(caracteresEnviados < limiteMinino || caracteresEnviados > limiteMaximo)
            throw new CampoComTamanhoInvalidoException(nomeCampo, limiteMinino, limiteMaximo, caracteresEnviados);
    }
}

