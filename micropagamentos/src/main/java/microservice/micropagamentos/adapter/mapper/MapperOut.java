package microservice.micropagamentos.adapter.mapper;

import microservice.micropagamentos.adapter.out.repository.entity.PagamentoEntity;
import microservice.micropagamentos.application.core.domain.Pagamento;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapperOut {

    PagamentoEntity toPagamentoEntity(Pagamento pagamento);

    Pagamento toPagamento(PagamentoEntity pagamentoEntity);

}

