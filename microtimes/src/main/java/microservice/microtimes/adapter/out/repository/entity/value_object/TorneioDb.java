package microservice.microtimes.adapter.out.repository.entity.value_object;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
@EqualsAndHashCode(of = {"id"})
public final class TorneioDb implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "id")
    private Long id;

}

