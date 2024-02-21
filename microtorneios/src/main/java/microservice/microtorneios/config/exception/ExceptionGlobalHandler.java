package microservice.microtorneios.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionGlobalHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ExceptionDetails> handleValidationException(ValidationException validationException) {

        var details = new ExceptionDetails(HttpStatus.BAD_REQUEST.value(), validationException.getMessage());

        return ResponseEntity
            .badRequest()
            .body(details);
    }
}

