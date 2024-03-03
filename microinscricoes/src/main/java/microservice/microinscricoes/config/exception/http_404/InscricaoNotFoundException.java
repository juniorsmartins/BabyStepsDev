package microservice.microinscricoes.config.exception.http_404;

import java.io.Serial;

public final class InscricaoNotFoundException extends RecursoNotFoundException {

  @Serial
  private static final long serialVersionUID = 1L;

  public InscricaoNotFoundException(String message) {
    super(message);
  }

  public InscricaoNotFoundException(final Long id) {
    this(String.format("A Inscrição %d não foi encontrada.", id));
  }
}

