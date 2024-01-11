package microservice.micronoticias.config.exception.http_409;

public final class NomenclaturaNaoUnicaException extends RegraDeNegocioVioladaException {

    public NomenclaturaNaoUnicaException(String nomenclatura) {
        super(String.format("A Editoria, com nomenclatura %s, já existe no banco de dados.", nomenclatura));
    }
}

