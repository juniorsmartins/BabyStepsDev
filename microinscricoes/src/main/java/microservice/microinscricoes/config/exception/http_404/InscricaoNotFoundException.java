package microservice.microinscricoes.config.exception.http_404;

import lombok.Getter;

import java.io.Serial;

@Getter
public final class InscricaoNotFoundException extends ResourceNotFoundException {

  @Serial
  private static final long serialVersionUID = 1L;

  public InscricaoNotFoundException(String message) {
    super(message);
  }

  public InscricaoNotFoundException(final Long id) {
    super("inscricao.nao.encontrada", id);
  }
}

