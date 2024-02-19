package microservice.microinscricoes.config.exception.http_404;

import java.io.Serial;

public final class TimeNotFoundException extends RecursoNaoEncontradoException {

  @Serial
  private static final long serialVersionUID = 1L;

  public TimeNotFoundException(String message) {
    super(message);
  }

  public TimeNotFoundException(final Long id) {
    this(String.format("O Time %d não foi encontrado.", id));
  }
}

