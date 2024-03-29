package microservice.micronoticias.config.exception.http_500;

import java.io.Serial;

public abstract sealed class ProblemaInternoNoServidorException extends RuntimeException
  permits EditoriaCriarUseCaseException, EditoriaSalvarAdapterException, EditoriaMapperOutImplException,
        NoticiaCriarUseCaseException, NoticiaSalvarAdapterException, EditoriaDeletarPorIdUseCaseException,
        ProhibitedNullValueException, FailedToUpdateEditorException, EditoriaUpdateUseCaseException,
        NoticiaDeleteUseCaseException, FailedToUpdateNewsException, NoticiaUpdateUseCaseException {

  @Serial
  private static final long serialVersionUID = 1L;

  protected ProblemaInternoNoServidorException(String mensagem) {
    super(mensagem);
  }
}

