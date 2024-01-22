package microservice.micronoticias.config.exception.http_404;

import java.io.Serial;

public final class NoticiaNotFoundException extends RecursoNaoEncontradoException {

  @Serial
  private static final long serialVersionUID = 1L;

  public NoticiaNotFoundException(String message) {
    super(message);
  }

  public NoticiaNotFoundException(Long id) {
    this(String.format("A Notícia %d não foi encontrada.", id));
  }
}

