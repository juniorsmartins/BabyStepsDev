package microservice.micronoticias.config.exception.http_404;

import microservice.micronoticias.config.exception.http_404.RecursoNaoEncontradoException;

import java.io.Serial;

public final class EditoriaNaoEncontradaException extends RecursoNaoEncontradoException {

  @Serial
  private static final long serialVersionUID = 1L;

  public EditoriaNaoEncontradaException(String mensagem) {
    super(mensagem);
  }

  public EditoriaNaoEncontradaException(Long id) {
    this(String.format("A Editoria %s não foi encontrada.", id));
  }
}

