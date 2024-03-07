package microservice.micropagamentos.application.core.domain;

import microservice.micropagamentos.application.core.domain.enums.EPagamentoStatus;
import microservice.micropagamentos.application.core.domain.enums.ETipoPagamento;

import java.time.OffsetDateTime;

public final class Pagamento {

    private Long id;

    private Long sagaEventId;

    private String transactionId;

    private Integer numeroBanco;

    private Integer numeroAgencia;

    private Integer numeroCartao;

    private ETipoPagamento tipo;

    private EPagamentoStatus status;

    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSagaEventId() {
        return sagaEventId;
    }

    public void setSagaEventId(Long sagaEventId) {
        this.sagaEventId = sagaEventId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
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

    public EPagamentoStatus getStatus() {
        return status;
    }

    public void setStatus(EPagamentoStatus status) {
        this.status = status;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Pagamento{" +
                "id=" + id +
                ", sagaEventId=" + sagaEventId +
                ", transactionId='" + transactionId + '\'' +
                ", numeroBanco=" + numeroBanco +
                ", numeroAgencia=" + numeroAgencia +
                ", numeroCartao=" + numeroCartao +
                ", tipo=" + tipo +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

