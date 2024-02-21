package microservice.microinscricoes.application.core.domain;

import java.time.LocalDateTime;

public final class Order {

    private Long id;

    private OrderInscrito orderInscrito;

    private LocalDateTime createdAt;

    private Long transactionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderInscrito getOrderInscrito() {
        return orderInscrito;
    }

    public void setOrderInscrito(OrderInscrito orderInscrito) {
        this.orderInscrito = orderInscrito;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }
}

