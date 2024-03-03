package microservice.microinscricoes.config.exception.http_404;

import lombok.Getter;

import java.io.Serial;

@Getter
public abstract sealed class ResourceNotFoundException extends RuntimeException permits TorneioNotFoundException,
      InscricaoNotFoundException, TimeNotFoundException {

  @Serial
  private static final long serialVersionUID = 1L;

  private Long id;

  private String messageKey;

  protected ResourceNotFoundException(String mensagem) {
    super(mensagem);
  }

  protected ResourceNotFoundException(String messageKey, final Long id) {
    super(messageKey);
    this.messageKey = messageKey;
    this.id = id;
  }
}

