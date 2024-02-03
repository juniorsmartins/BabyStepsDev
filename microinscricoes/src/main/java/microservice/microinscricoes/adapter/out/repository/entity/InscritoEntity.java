package microservice.microinscricoes.adapter.out.repository.entity;

import jakarta.persistence.*;
import lombok.*;
import microservice.microinscricoes.application.core.domain.enums.ETipoPagamento;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "inscritos")
@SecondaryTable(name = "inscrito_pagamento", pkJoinColumns = @PrimaryKeyJoinColumn(name = "inscrito_id"),
    foreignKey = @ForeignKey(name = "fk_inscrito_pagamento"))
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
public final class InscritoEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "inscricao_id")
    private InscricaoEntity inscricao;

    @Column(name = "time_id")
    private Long timeId;

    @Column(name = "numero_banco", table = "inscrito_pagamento")
    private Integer numeroBanco;

    @Column(name = "numero_agencia", table = "inscrito_pagamento")
    private Integer numeroAgencia;

    @Column(name = "numero_cartao", table = "inscrito_pagamento")
    private Integer numeroCartao;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", table = "inscrito_pagamento")
    private ETipoPagamento tipo;

}

