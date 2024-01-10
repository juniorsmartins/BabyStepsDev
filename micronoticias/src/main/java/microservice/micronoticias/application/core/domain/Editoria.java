package microservice.micronoticias.application.core.domain;

import microservice.micronoticias.application.core.useful.UtilityDomain;
import microservice.micronoticias.config.exception.http_409.CampoNuloProibidoException;

import java.util.Optional;

public final class Editoria {

    private static final int NOMENCLATURA_CARACTERES_MAXIMO = 100;

    private static final int NOMENCLATURA_CARACTERES_MININO = 3;

    private static final int DESCRICAO_CARACTERES_MAXIMO = 200;

    private static final int DESCRICAO_CARACTERES_MININO = 10;

    private Long id;

    private String nomenclatura;

    private String descricao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomenclatura() {
        return nomenclatura;
    }

    public void setNomenclatura(String valorCampo) {
        var nomeCampo = "Nomenclatura";

        Optional.ofNullable(valorCampo)
            .ifPresentOrElse(nome -> {
                UtilityDomain.validarCampoNaoVazioOuEmBranco(nomeCampo, nome);
                UtilityDomain.validarCampoComTamanhoValido(nomeCampo, NOMENCLATURA_CARACTERES_MININO,
                    NOMENCLATURA_CARACTERES_MAXIMO, nome.length());
                this.nomenclatura = nome;
            },
            () -> {throw new CampoNuloProibidoException(nomeCampo);}
        );
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String valorCampo) {
        var nomeCampo = "Descrição";

        Optional.ofNullable(valorCampo)
            .ifPresentOrElse(explicacao -> {
                UtilityDomain.validarCampoNaoVazioOuEmBranco(nomeCampo, explicacao);
                UtilityDomain.validarCampoComTamanhoValido(nomeCampo, DESCRICAO_CARACTERES_MININO,
                    DESCRICAO_CARACTERES_MAXIMO, explicacao.length());
                this.descricao = explicacao;
            }, () -> {throw new CampoNuloProibidoException(nomeCampo);}
        );
    }
}

