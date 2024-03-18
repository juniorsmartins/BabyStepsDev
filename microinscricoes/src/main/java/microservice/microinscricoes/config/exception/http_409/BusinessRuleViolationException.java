package microservice.microinscricoes.config.exception.http_409;

import lombok.Getter;

import java.io.Serial;

@Getter
public abstract sealed class BusinessRuleViolationException extends RuntimeException
        permits FiltersEventEmptyException, ConversionEnumSagaStatusFailedException {

  @Serial
  private static final long serialVersionUID = 1L;

  private final String messageKey;

  protected BusinessRuleViolationException(String messageKey) {
    super(messageKey);
    this.messageKey = messageKey;
  }
}

