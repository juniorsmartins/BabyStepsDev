package microservice.microtimes.adapter.out.repository.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Length;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "times")
@SecondaryTable(name = "time_sede", pkJoinColumns = @PrimaryKeyJoinColumn(name = "time_id"),
    foreignKey = @ForeignKey(name = "fk_time_sede"))
@SecondaryTable(name = "time_fundacao", pkJoinColumns = @PrimaryKeyJoinColumn(name = "time_id"),
    foreignKey = @ForeignKey(name = "fk_time_fundacao"))
@SecondaryTable(name = "time_staff", pkJoinColumns = @PrimaryKeyJoinColumn(name = "time_id"),
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


    // ---------- SEDE ----------
    @Column(name = "estado", table = "time_sede")
    private String estado;

    @Column(name = "cidade", table = "time_sede")
    private String cidade;


    // ---------- Fundação ----------
    @Column(name = "data", table = "time_fundacao")
    private LocalDate data;

    @Lob
    @Column(name = "descricao", length = Length.LOB_DEFAULT, table = "time_fundacao")
    private String descricao;


    // ---------- Staff ----------
    @Column(name = "presidente", table = "time_staff")
    private String presidente;

    @Column(name = "vice_presidente", table = "time_staff")
    private String vicePresidente;

    @Column(name = "head_coach", table = "time_staff")
    private String headCoach;
}

