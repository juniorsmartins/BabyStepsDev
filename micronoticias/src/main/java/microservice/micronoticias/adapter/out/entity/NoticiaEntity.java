package microservice.micronoticias.adapter.out.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "noticias")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
public final class NoticiaEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "chapeu", length = 30, nullable = false)
    private String chapeu;

    @Column(name = "titulo", length = 150, nullable = false)
    private String titulo;

    @Column(name = "linha_fina", length = 250, nullable = false)
    private String linhaFina;

    @Lob
    @Column(name = "lide", length = 400, nullable = false)
    private String lide;

    @Lob
    @Column(name = "corpo", length = 5000, nullable = false)
    private String corpo;

    @ElementCollection(fetch = FetchType.EAGER, targetClass = String.class)
    @CollectionTable(name = "noticia_autoria",
        joinColumns = @JoinColumn(name = "noticia_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_noticia_autoria")))
    @Column(name = "autoria", length = 100)
    private List<String> autorias;

    @ElementCollection(fetch = FetchType.EAGER, targetClass = String.class)
    @CollectionTable(name = "noticia_fonte",
        joinColumns = @JoinColumn(name = "noticia_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_noticia_fonte")))
    @Column(name = "fonte", length = 250)
    private List<String> fontes;
}

