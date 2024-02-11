package microservice.microinscricoes.adapter.in.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import microservice.microinscricoes.adapter.in.dto.TorneioIdDto;

import java.math.BigDecimal;
import java.time.LocalDate;

@Schema(name = "InscricaoOpenDtoIn", description = "Objeto de transporte de dados.")
@Builder
public record InscricaoOpenDtoIn(

    @Schema(name = "torneioId", description = "Identificador Exclusivo - para identificar um torneio no banco de dados.", type = "Long", example = "22")
    @NotNull
    @Valid
    TorneioIdDto torneio,

    @NotNull
//    @CheckedDate
//    @Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}", message = "Formato de data inválido. Utilize o formato dd/MM/yyyy.")
//    @DateTimeFormat(pattern = "dd/MM/yyyy")
    LocalDate dataInicio,

    @NotNull
//    @CheckedDate
//    @Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}", message = "Formato de data inválido. Utilize o formato dd/MM/yyyy.")
//    @DateTimeFormat(pattern = "dd/MM/yyyy")
    LocalDate dataFim,

    @NotNull
    BigDecimal valor

) { }

