package microservice.micronoticias.config.exception.http_409;

public final class RuleWithProhibitedNullValueException extends RegraDeNegocioVioladaException {

    public RuleWithProhibitedNullValueException(String nomeRegra) {
        super(String.format("A regra %s não pode receber valor nulo.", nomeRegra));
    }
}

