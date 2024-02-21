package microservice.microinscricoes.adapter.in.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import microservice.microinscricoes.adapter.in.controller.dto.TimeIdDto;
import microservice.microinscricoes.adapter.in.controller.dto.InscricaoIdDto;
import microservice.microinscricoes.application.core.domain.enums.ETipoPagamento;

@Schema(name = "InscrictoRegisterDtoIn", description = "Objeto de transporte de dados.")
@Builder
public record InscritoRegisterDtoIn(

    @Schema(name = "inscricao", description = "Estrutura de transporte de informações sobre inscrição.", type = "InscricaoIdDto")
    @NotNull
    @Valid
    InscricaoIdDto inscricao,

    @Schema(name = "time", description = "Estrutura de transporte de informações sobre time.", type = "TimeIdDto")
    @NotNull
    @Valid
    TimeIdDto time,

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

