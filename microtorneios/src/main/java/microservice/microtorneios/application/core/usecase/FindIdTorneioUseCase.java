package microservice.microtorneios.application.core.usecase;

import microservice.microtorneios.application.core.domain.EventFindIdTorneio;
import microservice.microtorneios.application.port.input.FindIdTorneioInputPort;
import microservice.microtorneios.application.port.output.ExistIdTorneioOutputPort;

public class FindIdTorneioUseCase implements FindIdTorneioInputPort {

    private final ExistIdTorneioOutputPort existIdTorneioOutputPort;

    public FindIdTorneioUseCase(ExistIdTorneioOutputPort existIdTorneioOutputPort) {
        this.existIdTorneioOutputPort = existIdTorneioOutputPort;
    }

    @Override
    public EventFindIdTorneio findId(EventFindIdTorneio eventFindIdTorneio) {

        return null;
    }
}

