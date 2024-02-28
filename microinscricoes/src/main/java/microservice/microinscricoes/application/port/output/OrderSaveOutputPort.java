package microservice.microinscricoes.application.port.output;

import microservice.microinscricoes.application.core.domain.Order;

public interface OrderSaveOutputPort {

    Order save(Order order);
}

