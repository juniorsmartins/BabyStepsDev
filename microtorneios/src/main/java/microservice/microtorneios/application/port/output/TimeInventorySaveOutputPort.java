package microservice.microtorneios.application.port.output;

import microservice.microtorneios.application.core.domain.TimeInventory;

public interface TimeInventorySaveOutputPort {

    void save(TimeInventory timeInventory);
}

