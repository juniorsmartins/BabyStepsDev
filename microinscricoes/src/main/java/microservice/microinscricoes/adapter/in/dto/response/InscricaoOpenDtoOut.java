package microservice.microinscricoes.adapter.in.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import microservice.microinscricoes.adapter.in.dto.TorneioIdDto;
import microservice.microinscricoes.application.core.domain.enums.EInscricaoStatus;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class InscricaoOpenDtoOut implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private TorneioIdDto torneio;

    private String dataInicio;

    private String dataFim;

    private BigDecimal valor;

    private EInscricaoStatus inscricaoStatus;
}

