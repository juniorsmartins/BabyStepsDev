package microservice.micronoticias.config.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.List;

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class ApiError {

    // https://www.rfc-editor.org/rfc/rfc7807

    private String type; // Uma referência URI que identifica o tipo do problema. (RFC 7807)

    private String title; // Um resumo curto e legível do problema. (RFC 7807)

    private int status; // O código de status HTTP gerado pelo servidor de origem para a ocorrência do problema. (RFC 7807)

    private String statusText; // Mostra a descrição do código de status HTTP (fora do padrão)

    private String detail; // Uma explicação legível específica para esta ocorrência do problema. (RFC 7807)

    private String instance; // Uma referência URI que identifica a específica ocorrência do problema. (RFC 7807)

    private Instant dataHoraErro; // Mostra o momento do erro (fora do padrão)

    private List<ParametroInvalido> parametrosInvalidos; // (RFC 7807)

    @Builder
    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ParametroInvalido {

        private String anotacaoViolada; // Nome da anotação violada do Bean Validation (fora do padrão)

        private String localDeErro; // Nome do campo em que a anotação foi violada (RFC 7807)

        private String motivo; // Explicação sobre o motivo do erro (RFC 7807)
    }
}

