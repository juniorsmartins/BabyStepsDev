package microservice.microtorneios.application.core.domain;

import lombok.ToString;
import microservice.microtorneios.application.core.domain.enums.ActivityStatusEnum;

@ToString
public final class TimeInventory {

    private Long id;

    private String nomeFantasia;

    private String estado;

    private ActivityStatusEnum status;

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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public ActivityStatusEnum getStatus() {
        return status;
    }

    public void setStatus(ActivityStatusEnum status) {
        this.status = status;
    }
}

