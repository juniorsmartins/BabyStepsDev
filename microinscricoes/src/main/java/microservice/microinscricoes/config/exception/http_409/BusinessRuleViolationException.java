package microservice.microinscricoes.config.exception.http_409;

import lombok.Getter;

import java.io.Serial;

@Getter
public abstract sealed class BusinessRuleViolationException extends RuntimeException permits FiltersEventEmptyException {

  @Serial
  private static final long serialVersionUID = 1L;

  private final Long id;

  private final String messageKey;

  protected BusinessRuleViolationException(String messageKey, final Long id) {
    super(messageKey);
    this.messageKey = messageKey;
    this.id = id;
  }

  protected BusinessRuleViolationException(String messageKey) {
    super(messageKey);
    this.messageKey = messageKey;
    this.id = 0L;
  }
}

