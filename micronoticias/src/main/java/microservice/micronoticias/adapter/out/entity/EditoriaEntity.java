package microservice.micronoticias.adapter.out.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "editorias",
    uniqueConstraints = {@UniqueConstraint(name = "uk_nomenclatura", columnNames = "nomenclatura")},
    indexes = {@Index(name = "idx_nomenclatura", columnList = "nomenclatura")})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"nomenclatura"})
public final class EditoriaEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nomenclatura", length = 100, nullable = false)
    private String nomenclatura;

    @Column(name = "descricao", length = 200, nullable = false)
    private String descricao;

    @ManyToMany(mappedBy = "editorias", targetEntity = NoticiaEntity.class)
    private Set<NoticiaEntity> noticias;
}

