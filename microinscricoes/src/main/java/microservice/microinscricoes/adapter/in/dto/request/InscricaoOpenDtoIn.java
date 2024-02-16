package microservice.microinscricoes.adapter.in.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import microservice.microinscricoes.adapter.in.dto.TorneioIdDto;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Schema(name = "InscricaoOpenDtoIn", description = "Objeto de transporte de dados.")
@Builder
public record InscricaoOpenDtoIn(

    @Schema(name = "torneio", description = "Estrutura para transporte de informações de Torneio.", type = "TorneioIdDto")
    @NotNull
    @Valid
    TorneioIdDto torneio,

    @Schema(name = "dataInicio", description = "Estrutura para transporte de informações de Torneio.", type = "String", example = "10/02/2024")
    @NotNull
//    @CheckedDate
    @Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}", message = "Formato de data inválido. Utilize o formato dd/MM/yyyy.")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    String dataInicio,

    @NotNull
//    @CheckedDate
//    @Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}", message = "Formato de data inválido. Utilize o formato dd/MM/yyyy.")
//    @DateTimeFormat(pattern = "dd/MM/yyyy")
    LocalDate dataFim,

    @NotNull
    BigDecimal valor

) { }

