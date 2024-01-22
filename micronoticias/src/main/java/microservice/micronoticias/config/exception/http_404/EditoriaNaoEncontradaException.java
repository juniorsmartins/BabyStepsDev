package microservice.micronoticias.config.exception.http_404;

import java.io.Serial;

public final class EditoriaNaoEncontradaException extends RecursoNaoEncontradoException {

  @Serial
  private static final long serialVersionUID = 1L;

  public EditoriaNaoEncontradaException(String message) {
    super(message);
  }

  public EditoriaNaoEncontradaException(Long id) {
    this(String.format("A Editoria %d não foi encontrada.", id));
  }
}

