package microservice.microinscricoes.application.port.output;

import microservice.microinscricoes.application.core.domain.Inscricao;

public interface KafkaProducerFindIdTorneioOutputPort {

    void sendFindIdTorneioEvent(Inscricao inscricao);
}

