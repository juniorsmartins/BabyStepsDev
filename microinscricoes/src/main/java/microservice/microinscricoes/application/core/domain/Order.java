package microservice.microinscricoes.application.core.domain;

import java.time.LocalDateTime;

public final class Order {

    private Long id;

    private Inscrito inscrito;

    private LocalDateTime createdAt;

    public Order() {}

    public Order(Inscrito inscrito) {
        this.inscrito = inscrito;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Inscrito getInscrito() {
        return inscrito;
    }

    public void setInscrito(Inscrito inscrito) {
        this.inscrito = inscrito;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

