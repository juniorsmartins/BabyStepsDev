package microservice.microtorneios.adapters.out.repository.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.Year;
import java.util.Set;

@Entity
@Table(name = "torneios")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id"})
public final class TorneioEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "ano", nullable = false)
    private Year ano;

    @ManyToMany
    @JoinTable(name = "torneio_time",
        joinColumns = @JoinColumn(name = "torneio_id"),
        inverseJoinColumns = @JoinColumn(name = "time_id")
    )
    private Set<TimeInventoryEntity> times;
}

