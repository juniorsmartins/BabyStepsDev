package microservice.microtorneios.application.core.usecase;

import microservice.microtorneios.application.core.domain.Time;
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

    private Time convertToTimeInventory(Time time) {

        var newTime = new Time();
        newTime.setId(time.getId());
        newTime.setNomeFantasia(time.getNomeFantasia());
        newTime.setEstado(time.getEstado());
        newTime.setStatus(time.getStatus());

        return newTime;
    }
}

