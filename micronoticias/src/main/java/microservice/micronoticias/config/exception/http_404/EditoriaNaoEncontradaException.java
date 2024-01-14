package microservice.micronoticias.config.exception.http_404;

import java.io.Serial;

public final class EditoriaNaoEncontradaException extends RecursoNaoEncontradoException {

  @Serial
  private static final long serialVersionUID = 1L;

  public EditoriaNaoEncontradaException(String nomenclatura) {
    super(String.format("A Editoria %s não foi encontrada.", nomenclatura));
  }

  public EditoriaNaoEncontradaException(Long id) {
    this(String.format("A Editoria %s não foi encontrada.", id));
  }
}

