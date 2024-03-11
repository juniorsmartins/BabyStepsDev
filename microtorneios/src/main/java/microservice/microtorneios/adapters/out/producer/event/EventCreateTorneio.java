package microservice.microtorneios.adapters.out.producer.event;

import microservice.microtorneios.adapters.out.producer.dto.TorneioIdDto;

public record EventCreateTorneio(

    TorneioIdDto torneio

) { }

