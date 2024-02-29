package microservice.microinscricoes.config.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.*;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
@RequiredArgsConstructor
public final class ExceptionGlobalHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    // ---------- TRATAMENTO DE EXCEÇÕES DEFAULT ---------- //
    // ---------- Sobreescrever método de ResponseEntityExceptionHandler para customizar ---------- //
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders httpHeaders,
                                                                  HttpStatusCode httpStatusCode,
                                                                  WebRequest webRequest) {

        // ProblemDetail RFC 7807
        ProblemDetail problemDetail = ProblemDetail.forStatus(httpStatusCode);
        problemDetail.setType(URI.create("https://babystepsdev.com/erros/campos-invalidos"));
        problemDetail.setTitle("Um ou mais campos estão inválidos.");

        var fields = this.getFields(ex);

        problemDetail.setProperty("fields", fields);

        return super.handleExceptionInternal(ex, problemDetail, httpHeaders, httpStatusCode, webRequest);
    }

    // ---------- Métodos assessórios ---------- //
    private Map<String, String> getFields(BindException ex) {
        return ex.getBindingResult()
            .getAllErrors()
            .stream()
            .collect(Collectors.toMap(objectError -> ((FieldError) objectError).getField(),
                    objectError -> messageSource.getMessage(objectError, LocaleContextHolder.getLocale())));
    }

    // ---------- TRATAMENTO DE EXCEÇÕES CUSTOM ---------- //
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ProblemDetail> handleValidationException(ValidationException ex) {

        var problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle(ex.getMessage());
        problemDetail.setType(URI.create("https://babystepsdev.com/erros/campos-invalidos"));

        return ResponseEntity
            .badRequest()
            .body(problemDetail);
    }
}

