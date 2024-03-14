package microservice.micropagamentos.application.port.output;

public interface PagamentoExistsOutputPort {

    Boolean existsDuplicate(Long torneioId, Long timeId);

}

