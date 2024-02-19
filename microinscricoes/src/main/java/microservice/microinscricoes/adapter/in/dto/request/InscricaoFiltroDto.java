package microservice.microinscricoes.adapter.in.dto.request;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public final class InscricaoFiltroDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String id;

    private String torneioId;
}

