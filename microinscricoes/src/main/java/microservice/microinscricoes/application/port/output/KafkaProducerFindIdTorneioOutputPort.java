package microservice.microinscricoes.application.port.output;

public interface KafkaProducerFindIdTorneioOutputPort {

    void sendFindIdTorneioEvent(Long id);
}

