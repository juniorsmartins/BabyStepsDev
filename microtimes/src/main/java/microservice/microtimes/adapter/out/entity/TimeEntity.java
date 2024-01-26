package microservice.microtimes.adapter.out.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "times")
@SecondaryTable(name = "time_sede", pkJoinColumns = @PrimaryKeyJoinColumn(name = "time_id"),
    foreignKey = @ForeignKey(name = "fk_time_sede"))
@SecondaryTable(name = "time_fundacao", pkJoinColumns = @PrimaryKeyJoinColumn(name = "time_id"),
    foreignKey = @ForeignKey(name = "fk_time_fundacao"))
@SecondaryTable(name = "time_staff", pkJoinColumns = @PrimaryKeyJoinColumn(name = "time_staff"),
    foreignKey = @ForeignKey(name = "fk_time_staff"))
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
public final class TimeEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_fantasia", nullable = false)
    private String nomeFantasia;

    @Column(name = "razao_social")
    private String razaoSocial;

    @Column(name = "cnpj")
    private String cnpj;

    @Embedded
//    @AttributeOverrides({
//        @AttributeOverride(name = "estado", column = @Column(name = "estado", table = "time_sede")),
//        @AttributeOverride(name = "cidade", column = @Column(name = "cidade", table = "time_sede"))
//    })
    private SedeEntity sede;

    @Embedded
    private FundacaoEntity fundacao;

    @Embedded
    private StaffEntity staff;
}

