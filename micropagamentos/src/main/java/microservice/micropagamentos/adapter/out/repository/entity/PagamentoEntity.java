package microservice.micropagamentos.adapter.out.repository.entity;

import jakarta.persistence.*;
import lombok.*;
import microservice.micropagamentos.application.core.domain.enums.EPagamentoStatus;
import microservice.micropagamentos.application.core.domain.enums.ETipoPagamento;

import java.io.Serial;
import java.io.Serializable;
import java.time.OffsetDateTime;

@Entity
@Table(name = "pagamentos")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id"})
public final class PagamentoEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "saga_event_id", nullable = false)
    private Long sagaEventId;

    @Column(name = "transaction_id", nullable = false)
    private String transactionId;

    @Column(name = "torneio_id", nullable = false)
    private Long torneioId;

    @Column(name = "time_id", nullable = false)
    private Long timeId;

    @Column(name = "numero_banco", nullable = false)
    private Integer numeroBanco;

    @Column(name = "numero_agencia", nullable = false)
    private Integer numeroAgencia;

    @Column(name = "numero_cartao", nullable = false)
    private Integer numeroCartao;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private ETipoPagamento tipo;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private EPagamentoStatus status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    @PrePersist
    private void prePersist() {
        this.createdAt = OffsetDateTime.now();
        this.status = EPagamentoStatus.PENDING;
    }

    @PreUpdate
    private void preUpdate() {
        this.updatedAt = OffsetDateTime.now();
    }
}

