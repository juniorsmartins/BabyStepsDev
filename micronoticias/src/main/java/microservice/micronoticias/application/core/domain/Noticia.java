package microservice.micronoticias.application.core.domain;

import microservice.micronoticias.application.core.useful.UtilityDomain;
import microservice.micronoticias.config.exception.http_409.CampoNuloProibidoException;

import java.util.List;
import java.util.Optional;

public final class Noticia {

    public static final int CHAPEU_CARACTERES_MAXIMO = 30;

    public static final int CHAPEU_CARACTERES_MINIMO = 2;

    public static final int TITULO_CARACTERES_MAXIMO = 150;

    public static final int TITULO_CARACTERES_MINIMO = 20;

    public static final int LINHAFINA_CARACTERES_MAXIMO = 250;

    public static final int LINHAFINA_CARACTERES_MINIMO = 80;

    public static final int LIDE_CARACTERES_MAXIMO = 400;

    public static final int LIDE_CARACTERES_MINIMO = 80;

    public static final int CORPO_CARACTERES_MAXIMO = 5000;

    public static final int CORPO_CARACTERES_MINIMO = 100;

    public static final int AUTORIA_CARACTERES_MAXIMO = 100;

    public static final int AUTORIA_CARACTERES_MINIMO = 3;

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

    public void setLide(String valorCampo) {
        var nomeCampo = "Lide";

        Optional.ofNullable(valorCampo)
            .ifPresentOrElse(lead -> {
                UtilityDomain.validarCampoNaoVazioOuEmBranco(nomeCampo, lead);
                UtilityDomain.validarCampoComTamanhoValido(nomeCampo, LIDE_CARACTERES_MINIMO,
                    LIDE_CARACTERES_MAXIMO, lead.length());
                this.lide = lead;
            },
            () -> {throw new CampoNuloProibidoException(nomeCampo);}
        );
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
        var nomeCampo = "Autorias";

        Optional.ofNullable(autorias)
            .ifPresentOrElse(authors -> {
                UtilityDomain.validarListaComValoresNaoVazioOuEmBranco(nomeCampo, authors);
                UtilityDomain.validarListaComValoresEmTamanhoValido(nomeCampo, authors,
                    AUTORIA_CARACTERES_MINIMO, AUTORIA_CARACTERES_MAXIMO);
                this.autorias = authors;
            },
            () -> this.autorias = autorias
        );
    }

    public List<String> getFontes() {
        return fontes;
    }

    public void setFontes(List<String> fontes) {
        this.fontes = fontes;
    }
}

