package microservice.microinscricoes.adapter.in.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import microservice.microinscricoes.adapter.in.dto.InscricaoIdDto;
import microservice.microinscricoes.application.core.domain.enums.ETipoPagamento;

@Schema(name = "InscrictoRegisterDtoIn", description = "Objeto de transporte de dados.")
@Builder
public record InscritoRegisterDtoIn(

    @Schema(name = "inscricaoId", description = "Identificador Exclusivo - para identificar uma inscrição no banco de dados.", type = "Long", example = "22")
    @NotNull
    @Valid
    InscricaoIdDto inscricao,

    @Schema(name = "timeId", description = "Identificador Exclusivo - para identificar um time no banco de dados.", type = "Long", example = "22")
    @NotNull
    @Positive
    Long timeId,

    @Schema(name = "numeroBanco", description = "Informação sobre número do banco para pagamento da inscrição.", type = "Integer", example = "103")
    @NotNull
    Integer numeroBanco,

    @Schema(name = "numeroAgencia", description = "Informação sobre número da agência para pagamento da inscrição.", type = "Integer", example = "12345")
    @NotNull
    Integer numeroAgencia,

    @Schema(name = "numeroCartao", description = "Informação sobre número do cartão para pagamento da inscrição.", type = "Integer", example = "123478")
    @NotNull
    Integer numeroCartao,

    @Schema(name = "tipo", description = "Informação sobre tipo (débito ou crédito) para pagamento da inscrição.", type = "ETipoPagamento", example = "DEBITO")
    @NotNull
    ETipoPagamento tipo

) { }

