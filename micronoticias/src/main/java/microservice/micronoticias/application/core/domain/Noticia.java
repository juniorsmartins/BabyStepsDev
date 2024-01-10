package microservice.micronoticias.application.core.domain;

import microservice.micronoticias.application.core.useful.UtilityDomain;
import microservice.micronoticias.config.exception.http_409.CampoNuloProibidoException;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    public static final int FONTE_CARACTERES_MAXIMO = 250;

    public static final int FONTE_CARACTERES_MINIMO = 3;

    private Long id;

    private String chapeu;

    private String titulo;

    private String linhaFina;

    private String lide;

    private String corpo;

    private List<String> autorias;

    private List<String> fontes;

    private Set<Editoria> editorias;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public void setCorpo(String valorCampo) {
        var nomeCampo = "Corpo";

        Optional.ofNullable(valorCampo)
            .ifPresentOrElse(body -> {
                UtilityDomain.validarCampoNaoVazioOuEmBranco(nomeCampo, body);
                UtilityDomain.validarCampoComTamanhoValido(nomeCampo, CORPO_CARACTERES_MINIMO,
                    CORPO_CARACTERES_MAXIMO, body.length());
                this.corpo = body;
            },
            () -> {throw new CampoNuloProibidoException(nomeCampo);}
        );
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
        var nomeCampo = "Fontes";

        Optional.ofNullable(fontes)
            .ifPresentOrElse(sources -> {
                UtilityDomain.validarListaComValoresNaoVazioOuEmBranco(nomeCampo, sources);
                UtilityDomain.validarListaComValoresEmTamanhoValido(nomeCampo, sources,
                    FONTE_CARACTERES_MINIMO, FONTE_CARACTERES_MAXIMO);
                this.fontes = sources;
            },
            () -> this.fontes = fontes
        );
    }

    public Set<Editoria> getEditorias() {
        return editorias;
    }

    public void setEditorias(Set<Editoria> editorias) {
        this.editorias = editorias;
    }

    @Override
    public String toString() {
        return "Noticia{" +
                "id=" + id +
                ", chapeu='" + chapeu + '\'' +
                ", titulo='" + titulo + '\'' +
                ", linhaFina='" + linhaFina + '\'' +
                ", lide='" + lide + '\'' +
                ", corpo='" + corpo + '\'' +
                ", autorias=" + autorias +
                ", fontes=" + fontes +
                '}';
    }
}

