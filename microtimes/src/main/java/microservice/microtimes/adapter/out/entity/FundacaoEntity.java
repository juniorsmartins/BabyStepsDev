package microservice.microtimes.adapter.out.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
@Getter
@Setter
public final class FundacaoEntity implements Serializable {

    @Column(name = "data", table = "time_fundacao")
    private LocalDate data;

    @Column(name = "descricao", table = "time_fundacao")
    private String descricao;
}

