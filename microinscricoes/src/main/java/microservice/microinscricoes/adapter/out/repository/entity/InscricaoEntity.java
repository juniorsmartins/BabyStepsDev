package microservice.microinscricoes.adapter.out.repository.entity;

import jakarta.persistence.*;
import lombok.*;
import microservice.microinscricoes.application.core.domain.enums.EInscricaoStatus;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "inscricoes")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
public final class InscricaoEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "torneio_id")
    private Long torneioId;

    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @Column(name = "data_fim")
    private LocalDate dataFim;

    @Column(name = "valor")
    private BigDecimal valor;

    @OneToMany(mappedBy = "inscricao")
    private Set<InscritoEntity> inscritos;

    @Enumerated(EnumType.STRING)
    @Column(name = "inscricao_status")
    private EInscricaoStatus inscricaoStatus;

}

