package microservice.microinscricoes.config.exception.http_404;

import lombok.RequiredArgsConstructor;
import microservice.microinscricoes.config.exception.MessagesDefault;

import java.io.Serial;

public final class InscricaoNotFoundException extends RecursoNotFoundException {

  @Serial
  private static final long serialVersionUID = 1L;

  public InscricaoNotFoundException(String message) {
    super(message);
  }

//  public InscricaoNotFoundException(final Long id) {
//    this(String.format("A Inscrição %d não foi encontrada.", id));
//  }

  public InscricaoNotFoundException(final Long id) {
    this(String.format(new MessagesDefault().getInscricao_nao_encontrada(), id));
//    this(MessagesDefault.inscricao_nao_encontrada + " " + id);
  }
}

