package microservice.microinscricoes.adapter.in.controller.dto.custom_annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CheckedDateValidator implements ConstraintValidator<CheckedDate, String> {

    @Override
    public boolean isValid(String data, ConstraintValidatorContext context) {
        // Formatador para o padrão da data
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            // Conversão da String para LocalDate
            LocalDate.parse(data, formatter);
        } catch (Exception ex) {
            return false;
        }

        return true;
    }
}

