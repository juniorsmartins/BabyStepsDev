package microservice.microinscricoes.adapter.in.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import microservice.microinscricoes.adapter.in.dto.InscricaoIdDto;
import microservice.microinscricoes.adapter.in.dto.PagamentoDto;

@Schema(name = "InscrictoRegisterDtoOut", description = "Objeto de transporte de dados.")
@JsonInclude(JsonInclude.Include.NON_NULL)
public record InscritoRegisterDtoOut(

    @Schema(name = "id", description = "Identificador Exclusivo - para identificar um inscrito no banco de dados.", type = "Long", example = "22", required = true)
    Long id,

    @Schema(name = "inscricaoId", description = "Identificador Exclusivo - para identificar uma inscrição no banco de dados.", type = "Long", example = "22", required = true)
    InscricaoIdDto inscricao,

    @Schema(name = "timeId", description = "Identificador Exclusivo - para identificar um time no banco de dados.", type = "Long", example = "22", required = true)
    Long timeId,

    @Schema(name = "pagamento", description = "Informações para efetuar pagamento da inscrição.", type = "PagamentoDtoIn", required = true)
    PagamentoDto pagamento

) { }

