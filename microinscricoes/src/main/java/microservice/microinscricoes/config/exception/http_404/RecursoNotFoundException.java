package microservice.microinscricoes.config.exception.http_404;

import java.io.Serial;

public abstract sealed class RecursoNotFoundException extends RuntimeException permits TorneioNotFoundException,
      InscricaoNotFoundException, TimeNotFoundException {

  @Serial
  private static final long serialVersionUID = 1L;

  protected RecursoNotFoundException(String mensagem) {
    super(mensagem);
  }
}

