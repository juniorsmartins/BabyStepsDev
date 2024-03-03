package microservice.microinscricoes.config.exception.http_404;

import java.io.Serial;

public final class InscricaoNotFoundException extends ResourceNotFoundException {

  @Serial
  private static final long serialVersionUID = 1L;

  public InscricaoNotFoundException(final Long id) {
    super("inscricao.nao.encontrada", id);
  }
}

