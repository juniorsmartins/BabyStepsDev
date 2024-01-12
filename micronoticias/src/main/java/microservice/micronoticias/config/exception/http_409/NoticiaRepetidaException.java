package microservice.micronoticias.config.exception.http_409;

public final class NoticiaRepetidaException extends RegraDeNegocioVioladaException {

    public NoticiaRepetidaException(String titulo) {
        super(String.format("A Notícia, com título '%s', já existe no banco de dados.", titulo));
    }
}

