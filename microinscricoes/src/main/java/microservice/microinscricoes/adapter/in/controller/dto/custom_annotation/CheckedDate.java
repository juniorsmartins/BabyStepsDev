package microservice.microinscricoes.adapter.in.controller.dto.custom_annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {CheckedDateValidator.class})
public @interface CheckedDate {

    String message() default "Data em formato inválido. Envie na seguinte forma: dd/MM/yyyy.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

