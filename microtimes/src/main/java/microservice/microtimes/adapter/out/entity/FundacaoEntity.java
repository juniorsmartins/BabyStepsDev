package microservice.microtimes.adapter.out.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Lob;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Length;

import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public final class FundacaoEntity implements Serializable {

    @Column(name = "data", table = "time_fundacao")
    private LocalDate data;

    @Lob
    @Column(name = "descricao", length = Length.LOB_DEFAULT, table = "time_fundacao")
    private String descricao;
}

