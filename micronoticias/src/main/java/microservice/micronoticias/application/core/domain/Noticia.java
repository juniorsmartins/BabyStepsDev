package microservice.micronoticias.application.core.domain;

import microservice.micronoticias.config.exception.http_409.CampoComTamanhoInvalidoException;
import microservice.micronoticias.config.exception.http_409.CampoNuloProibidoException;
import microservice.micronoticias.config.exception.http_409.CampoVazioOuEmBrancoProibidoException;

import java.util.List;
import java.util.Optional;

public final class Noticia {

    public static final int CHAPEU_CARACTERES_MAXIMO = 30;

    public static final int CHAPEU_CARACTERES_MINIMO = 2;

    private String chapeu;

    private String titulo;

    private String linhaFina;

    private String lide;

    private String corpo;

    private List<String> autorias;

    private List<String> fontes;

    public String getChapeu() {
        return chapeu;
    }

    public void setChapeu(String valorCampo) {
        var nomeCampo = "Chapéu";

        Optional.ofNullable(valorCampo)
            .ifPresentOrElse(hat -> {
                this.validarCampoNaoVazioOuEmBranco(nomeCampo, valorCampo);
                this.validarCampoComTamanhoValido(nomeCampo, CHAPEU_CARACTERES_MINIMO,
                    CHAPEU_CARACTERES_MAXIMO, valorCampo.length());
                this.chapeu = valorCampo;
            },
            () -> {throw new CampoNuloProibidoException(nomeCampo);}
        );
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getLinhaFina() {
        return linhaFina;
    }

    public void setLinhaFina(String linhaFina) {
        this.linhaFina = linhaFina;
    }

    public String getLide() {
        return lide;
    }

    public void setLide(String lide) {
        this.lide = lide;
    }

    public String getCorpo() {
        return corpo;
    }

    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }

    public List<String> getAutorias() {
        return autorias;
    }

    public void setAutorias(List<String> autorias) {
        this.autorias = autorias;
    }

    public List<String> getFontes() {
        return fontes;
    }

    public void setFontes(List<String> fontes) {
        this.fontes = fontes;
    }

    private void validarCampoNaoVazioOuEmBranco(String nomeCampo, String valorCampo) {
        if(valorCampo.isBlank())
            throw new CampoVazioOuEmBrancoProibidoException(nomeCampo);
    }

    private void validarCampoComTamanhoValido(String nomeCampo, int limiteMinino, int limiteMaximo, int caracteresEnviados) {
        if(caracteresEnviados < limiteMinino || caracteresEnviados > limiteMaximo)
            throw new CampoComTamanhoInvalidoException(nomeCampo, limiteMinino, limiteMaximo, caracteresEnviados);
    }
}

