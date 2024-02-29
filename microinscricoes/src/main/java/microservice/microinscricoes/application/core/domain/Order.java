package microservice.microinscricoes.application.core.domain;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

public final class Order {

    private Long id;

    private Inscrito inscrito;

    private OffsetDateTime createdAt;

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

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

