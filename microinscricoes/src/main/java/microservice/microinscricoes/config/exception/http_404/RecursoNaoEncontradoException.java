package microservice.microinscricoes.config.exception.http_404;

import java.io.Serial;

public abstract sealed class RecursoNaoEncontradoException extends RuntimeException permits TorneioNotFoundException,
      InscricaoNotFoundException {

  @Serial
  private static final long serialVersionUID = 1L;

  protected RecursoNaoEncontradoException(String mensagem) {
    super(mensagem);
  }
}

