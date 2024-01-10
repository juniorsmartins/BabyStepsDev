package microservice.micronoticias.config.exception.http_500;

import java.io.Serial;

public abstract class ProblemaInternoNoServidorException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = 1L;

  protected ProblemaInternoNoServidorException(String mensagem) {
    super(mensagem);
  }
}

