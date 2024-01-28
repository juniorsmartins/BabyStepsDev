package microservice.microtorneios.application.core.domain;

import microservice.microtorneios.application.core.domain.enums.ActivityStatusEnum;

public final class TimeInventory {

    private Long id;

    private String nomeFantasia;

    private String status;

    private ActivityStatusEnum estado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ActivityStatusEnum getEstado() {
        return estado;
    }

    public void setEstado(ActivityStatusEnum estado) {
        this.estado = estado;
    }
}

