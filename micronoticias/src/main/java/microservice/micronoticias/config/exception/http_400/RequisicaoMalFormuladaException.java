package microservice.micronoticias.config.exception.http_400;

import java.io.Serial;

public abstract class RequisicaoMalFormuladaException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = 1L;

  protected RequisicaoMalFormuladaException(String mensagem) {
    super(mensagem);
  }
}

