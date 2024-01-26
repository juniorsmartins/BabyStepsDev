package microservice.microtimes.adapter.out.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
public final class SedeEntity implements Serializable {

    @Column(name = "estado", table = "time_sede")
    private String estado;

    @Column(name = "cidade", table = "time_sede")
    private String cidade;
}

