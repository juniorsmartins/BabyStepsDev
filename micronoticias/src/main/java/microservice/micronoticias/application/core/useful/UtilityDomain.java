package microservice.micronoticias.application.core.useful;

import microservice.micronoticias.config.exception.http_409.CampoComTamanhoInvalidoException;
import microservice.micronoticias.config.exception.http_409.CampoVazioOuEmBrancoProibidoException;

import java.util.List;

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

    public static void validarListaComValoresNaoVazioOuEmBranco(String nomeCampo, List<String> lista) {

        lista.stream()
            .filter(String::isBlank)
            .findAny()
            .ifPresent(autor -> {throw new CampoVazioOuEmBrancoProibidoException(nomeCampo);});
    }

    public static void validarListaComValoresEmTamanhoValido(String nomeCampo, List<String> lista,
                                                             int limiteMinimo, int limiteMaximo) {
        lista.forEach(valor -> validarCampoComTamanhoValido(nomeCampo, limiteMinimo, limiteMaximo, valor.length()));
    }
}

