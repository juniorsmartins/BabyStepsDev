package microservice.microtorneios.application.core.usecase;

import microservice.microtorneios.application.core.domain.Time;
import microservice.microtorneios.application.core.domain.TimeInventory;
import microservice.microtorneios.application.port.input.TimeInventoryCreateInputPort;
import microservice.microtorneios.application.port.output.TimeInventorySaveOutputPort;

public class TimeInventoryCreateUseCase implements TimeInventoryCreateInputPort {

    private final TimeInventorySaveOutputPort timeInventorySaveOutputPort;

    public TimeInventoryCreateUseCase(TimeInventorySaveOutputPort timeInventorySaveOutputPort) {
        this.timeInventorySaveOutputPort = timeInventorySaveOutputPort;
    }

    @Override
    public void create(Time time) {

        var timeInventory = this.convertToTimeInventory(time);
        this.timeInventorySaveOutputPort.save(timeInventory);
    }

    private TimeInventory convertToTimeInventory(Time time) {

        var timeInventory = new TimeInventory();
        timeInventory.setId(time.getId());
        timeInventory.setNomeFantasia(time.getNomeFantasia());
        timeInventory.setEstado(time.getEstado());
        timeInventory.setStatus(time.getStatus());

        return timeInventory;
    }
}

