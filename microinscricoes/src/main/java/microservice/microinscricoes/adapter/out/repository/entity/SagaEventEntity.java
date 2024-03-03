package microservice.microinscricoes.adapter.out.repository.entity;

import jakarta.persistence.*;
import lombok.*;
import microservice.microinscricoes.adapter.out.repository.entity.value_object.HistoryDb;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "saga_events")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id"})
public final class SagaEventEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transaction_id")
    private Long transactionId;

    @Column(name = "inscricao_id")
    private Long inscricaoId;

    @Column(name = "inscrito_id")
    private Long inscritoId;

    @Column(name = "torneio_id")
    private Long torneioId;

    @Column(name = "time_id")
    private Long timeId;

//    @OneToOne(optional = false)
//    @JoinColumn(name = "payload_id")
//    private OrderEntity payload;

    @OneToOne(optional = false)
    @JoinColumn(name = "payload_id")
    private InscritoEntity payload;

    @Column(name = "source")
    private String source;

    @Column(name = "status")
    private String status;

    @ElementCollection
    @CollectionTable(name = "saga_event_histories",
    joinColumns = @JoinColumn(name = "saga_event_id"))
    private List<HistoryDb> eventHistories;

    @Column(name = "created_at", updatable = false)
    private OffsetDateTime createdAt;

    @PrePersist
    private void prePersist() {
        this.createdAt = OffsetDateTime.now();
    }
}

