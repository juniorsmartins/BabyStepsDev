package microservice.micronoticias.application.core.domain;

import microservice.micronoticias.application.core.useful.UtilityDomain;
import microservice.micronoticias.config.exception.http_409.CampoComTamanhoInvalidoException;
import microservice.micronoticias.config.exception.http_409.CampoNuloProibidoException;
import microservice.micronoticias.config.exception.http_409.CampoVazioOuEmBrancoProibidoException;

import java.util.List;
import java.util.Optional;

public final class Noticia {

    public static final int CHAPEU_CARACTERES_MAXIMO = 30;

    public static final int CHAPEU_CARACTERES_MINIMO = 2;

    public static final int TITULO_CARACTERES_MAXIMO = 150;

    public static final int TITULO_CARACTERES_MINIMO = 20;

    public static final int LINHAFINA_CARACTERES_MAXIMO = 250;

    public static final int LINHAFINA_CARACTERES_MINIMO = 80;

    public static final int LIDE_CARACTERES_MAXIMO = 401;

    public static final int LIDE_CARACTERES_MINIMO = 80;

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
                UtilityDomain.validarCampoNaoVazioOuEmBranco(nomeCampo, hat);
                UtilityDomain.validarCampoComTamanhoValido(nomeCampo, CHAPEU_CARACTERES_MINIMO,
                    CHAPEU_CARACTERES_MAXIMO, hat.length());
                this.chapeu = hat;
            },
            () -> {throw new CampoNuloProibidoException(nomeCampo);}
        );
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String valorCampo) {
        var nomeCampo = "Título";

        Optional.ofNullable(valorCampo)
            .ifPresentOrElse(title -> {
                UtilityDomain.validarCampoNaoVazioOuEmBranco(nomeCampo, title);
                UtilityDomain.validarCampoComTamanhoValido(nomeCampo, TITULO_CARACTERES_MINIMO,
                    TITULO_CARACTERES_MAXIMO, title.length());
                this.titulo = title;
            },
            () -> {throw new CampoNuloProibidoException(nomeCampo);}
        );
    }

    public String getLinhaFina() {
        return linhaFina;
    }

    public void setLinhaFina(String valorCampo) {
        var nomeCampo = "LinhaFina";

        Optional.ofNullable(valorCampo)
            .ifPresentOrElse(line -> {
                UtilityDomain.validarCampoNaoVazioOuEmBranco(nomeCampo, line);
                UtilityDomain.validarCampoComTamanhoValido(nomeCampo, LINHAFINA_CARACTERES_MINIMO,
                    LINHAFINA_CARACTERES_MAXIMO, line.length());
                this.linhaFina = line;
            },
            () -> {throw new CampoNuloProibidoException(nomeCampo);}
        );
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
}

