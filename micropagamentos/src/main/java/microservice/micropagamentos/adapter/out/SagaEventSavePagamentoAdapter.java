package microservice.micropagamentos.adapter.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.micropagamentos.adapter.out.repository.PagamentoRepository;
import microservice.micropagamentos.application.port.output.SagaEventSavePagamentoOutputPort;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class SagaEventSavePagamentoAdapter implements SagaEventSavePagamentoOutputPort {

    private final PagamentoRepository pagamentoRepository;


}

