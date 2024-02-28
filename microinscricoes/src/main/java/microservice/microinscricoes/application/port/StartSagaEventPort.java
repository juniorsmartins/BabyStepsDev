package microservice.microinscricoes.application.port;

import microservice.microinscricoes.application.core.domain.Inscrito;

public interface StartSagaEventPort {

    void sendEvent(Inscrito inscrito);
}

