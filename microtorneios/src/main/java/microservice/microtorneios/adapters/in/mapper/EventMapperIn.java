package microservice.microtorneios.adapters.in.mapper;

import microservice.microtorneios.adapters.in.event.EventFindIdTorneioDto;
import microservice.microtorneios.application.core.domain.EventFindIdTorneio;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventMapperIn {

    EventFindIdTorneio toEventFindIdTorneio(EventFindIdTorneioDto eventFindIdTorneioDto);
}

