package microservice.microinscricoes.adapter.in.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import microservice.microinscricoes.adapter.in.controller.dto.InscricaoIdDto;
import microservice.microinscricoes.adapter.in.controller.dto.TimeIdDto;
import microservice.microinscricoes.application.core.domain.enums.ETipoPagamento;

import java.time.OffsetDateTime;

@Schema(name = "InscrictoRegisterDtoOut", description = "Objeto de transporte de dados.")
@JsonInclude(JsonInclude.Include.NON_NULL)
public record InscritoCreateDtoOut(

    @Schema(name = "id", description = "Identificador Exclusivo - para identificar um inscrito no banco de dados.", type = "Long", example = "22")
    Long id,

    @Schema(name = "inscricaoId", description = "Identificador Exclusivo - para identificar uma inscrição no banco de dados.", type = "Long", example = "22")
    InscricaoIdDto inscricao,

    @Schema(name = "timeId", description = "Identificador Exclusivo - para identificar um time no banco de dados.", type = "Long", example = "22")
    TimeIdDto time,

    @Schema(name = "numeroBanco", description = "Informação sobre número do banco para pagamento da inscrição.", type = "Integer", example = "103")
    Integer numeroBanco,

    @Schema(name = "numeroAgencia", description = "Informação sobre número da agência para pagamento da inscrição.", type = "Integer", example = "12345")
    Integer numeroAgencia,

    @Schema(name = "numeroCartao", description = "Informação sobre número do cartão para pagamento da inscrição.", type = "Integer", example = "123478")
    Integer numeroCartao,

    @Schema(name = "tipo", description = "Informação sobre tipo (débito ou crédito) para pagamento da inscrição.", type = "ETipoPagamento", example = "DEBITO")
    ETipoPagamento tipo,

    @Schema(name = "createdAt", description = "Informação sobre data e hora do cadastro.", type = "OffsetDateTime", example = "AAAA-MM-DDTHH:mm:ss.sssXXX")
    OffsetDateTime createdAt

) { }

