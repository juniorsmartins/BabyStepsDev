package microservice.microtimes.config.exception;

import lombok.RequiredArgsConstructor;
import microservice.microtimes.config.exception.http_404.RecursoNotFoundException;
import microservice.microtimes.config.exception.http_409.BusinessRuleViolationException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
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
public class ExceptionGlobalHandler extends ResponseEntityExceptionHandler {

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
        problemDetail.setTitle(this.getMessage("resources.campos.invalidos"));

        var fields = this.getFields(ex);

        problemDetail.setProperty("fields", fields);

        return super.handleExceptionInternal(ex, problemDetail, httpHeaders, httpStatusCode, webRequest);
    }

    // ---------- Métodos assessórios ---------- //
    private String getMessage(String messageKey) {
        return this.messageSource.getMessage(messageKey, new Object[]{}, LocaleContextHolder.getLocale());
    }

    private Map<String, String> getFields(BindException ex) {
        return ex.getBindingResult()
            .getAllErrors()
            .stream()
            .collect(Collectors.toMap(objectError -> ((FieldError) objectError).getField(),
                objectError -> this.messageSource.getMessage(objectError, LocaleContextHolder.getLocale())));
    }


    // ---------- TRATAMENTO DE EXCEÇÕES CUSTOM ---------- //
    @ExceptionHandler(RecursoNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleResourceNotFound(RecursoNotFoundException ex, WebRequest webRequest) {

        // ProblemDetail RFC 7807
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setType(URI.create("https://babystepsdev.com/erros/recurso-nao-encontrado"));

        var id = ex.getId();

        var mensagem = this.messageSource.getMessage(ex.getMessageKey(), new Object[]{id},
            LocaleContextHolder.getLocale());

        problemDetail.setTitle(String.format(mensagem, id));

        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(problemDetail);
    }

    @ExceptionHandler(BusinessRuleViolationException.class)
    public ResponseEntity<ProblemDetail> handleBusinessRuleViolation(BusinessRuleViolationException ex,
                                                                     WebRequest webRequest) {
        // ProblemDetail RFC 7807
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        problemDetail.setType(URI.create("https://babystepsdev.com/erros/regras-de-negocio-violadas"));
        problemDetail.setTitle(this.getMessage(ex.getMessageKey()));

        return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body(problemDetail);
    }


    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ExceptionDetails> handleValidationException(ValidationException validationException) {

        var details = new ExceptionDetails(HttpStatus.BAD_REQUEST.value(), validationException.getMessage());

        return ResponseEntity
            .badRequest()
            .body(details);
    }
}

