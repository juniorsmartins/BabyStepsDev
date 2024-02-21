package microservice.microinscricoes.adapter.out.producer;

import microservice.microinscricoes.application.core.domain.History;
import microservice.microinscricoes.application.core.domain.Order;

import java.time.LocalDateTime;
import java.util.List;

public final class SagaEvent {

    private Long id;

    private Long transactionId;

    private Long inscritoId;

    private Long inscricaoId;

    private Order payload;

    private String source;

    private String status;

    private List<History> eventHistories;

    private LocalDateTime createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Long getInscritoId() {
        return inscritoId;
    }

    public void setInscritoId(Long inscritoId) {
        this.inscritoId = inscritoId;
    }

    public Long getInscricaoId() {
        return inscricaoId;
    }

    public void setInscricaoId(Long inscricaoId) {
        this.inscricaoId = inscricaoId;
    }

    public Order getPayload() {
        return payload;
    }

    public void setPayload(Order payload) {
        this.payload = payload;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<History> getEventHistories() {
        return eventHistories;
    }

    public void setEventHistories(List<History> eventHistories) {
        this.eventHistories = eventHistories;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

