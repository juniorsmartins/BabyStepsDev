package microservice.microinscricoes.adapter.in.controller.dto;

import jakarta.validation.constraints.Positive;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id"})
public final class TorneioIdDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Positive
    private Long id;
}

