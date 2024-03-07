package microservice.orchestrator.application.core.domain;

import microservice.orchestrator.application.core.domain.enums.ETipoPagamento;

public final class Inscrito {

    private Long id;

    private Long inscricaoId;

    private Time time;

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

    public Long getInscricaoId() {
        return inscricaoId;
    }

    public void setInscricaoId(Long inscricaoId) {
        this.inscricaoId = inscricaoId;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
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

    @Override
    public String toString() {
        return "Inscrito{" +
                "id=" + id +
                ", inscricaoId=" + inscricaoId +
                ", time=" + time +
                ", numeroBanco=" + numeroBanco +
                ", numeroAgencia=" + numeroAgencia +
                ", numeroCartao=" + numeroCartao +
                ", tipo=" + tipo +
                '}';
    }
}

