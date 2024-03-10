package microservice.microtorneios.application.core.usecase;

import microservice.microtorneios.application.core.domain.Time;
import microservice.microtorneios.application.port.input.TimeCreateInputPort;
import microservice.microtorneios.application.port.output.TimeSaveOutputPort;

public class TimeCreateUseCase implements TimeCreateInputPort {

    private final TimeSaveOutputPort timeInventorySaveOutputPort;

    public TimeCreateUseCase(TimeSaveOutputPort timeInventorySaveOutputPort) {
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

