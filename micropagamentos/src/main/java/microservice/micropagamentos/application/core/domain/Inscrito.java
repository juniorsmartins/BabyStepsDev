package microservice.micropagamentos.application.core.domain;

import microservice.microinscricoes.application.core.domain.enums.ETipoPagamento;

public final class Inscrito {

    private Long id;

    private Inscricao inscricao;

    private Long timeId;

    private Integer numeroBanco;

    private Integer numeroAgencia;

    private Integer numeroCartao;

    private ETipoPagamento tipo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Inscricao getInscricao() {
        return inscricao;
    }

    public void setInscricao(Inscricao inscricao) {
        this.inscricao = inscricao;
    }

    public Long getTimeId() {
        return timeId;
    }

    public void setTimeId(Long timeId) {
        this.timeId = timeId;
    }

    public Integer getNumeroBanco() {
        return numeroBanco;
    }

    public void setNumeroBanco(Integer numeroBanco) {
        this.numeroBanco = numeroBanco;
    }

    public Integer getNumeroAgencia() {
        return numeroAgencia;
    }

    public void setNumeroAgencia(Integer numeroAgencia) {
        this.numeroAgencia = numeroAgencia;
    }

    public Integer getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(Integer numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public ETipoPagamento getTipo() {
        return tipo;
    }

    public void setTipo(ETipoPagamento tipo) {
        this.tipo = tipo;
    }
}

