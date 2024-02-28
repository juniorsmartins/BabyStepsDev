package microservice.microinscricoes.adapter.out.producer.dto;

import lombok.*;
import microservice.microinscricoes.application.core.domain.Inscrito;
import microservice.microinscricoes.application.core.domain.enums.ETipoPagamento;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public final class OrderDtoRequest {

    private Long id;

    private Integer numeroBanco;

    private Integer numeroAgencia;

    private Integer numeroCartao;

    private ETipoPagamento tipo;

    private LocalDateTime createdAt;
}

