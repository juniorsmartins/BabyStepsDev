package microservice.microinscricoes.application.core.domain.filtro;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public final class FiltersEvent {

    private Long id;

    private String transactionId;
}

