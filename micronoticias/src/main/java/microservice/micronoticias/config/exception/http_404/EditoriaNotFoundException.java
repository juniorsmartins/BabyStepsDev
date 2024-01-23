package microservice.micronoticias.config.exception.http_404;

import java.io.Serial;

public final class EditoriaNotFoundException extends RecursoNaoEncontradoException {

  @Serial
  private static final long serialVersionUID = 1L;

  public EditoriaNotFoundException(String message) {
    super(message);
  }

  public EditoriaNotFoundException(Long id) {
    this(String.format("A Editoria %d não foi encontrada.", id));
  }
}

