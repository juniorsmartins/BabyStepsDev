package microservice.microinscricoes.adapter.out.repository.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.Year;

@Entity
@Table(name = "torneios")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
public final class TorneioEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "ano", nullable = false)
    private Year ano;
}

