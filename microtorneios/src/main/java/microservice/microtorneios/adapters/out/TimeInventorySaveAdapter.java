package microservice.microtorneios.adapters.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.microtorneios.adapters.out.repository.TimeInventoryRepository;
import microservice.microtorneios.adapters.out.repository.mapper.TimeInventoryOutMapper;
import microservice.microtorneios.application.core.domain.TimeInventory;
import microservice.microtorneios.application.port.output.TimeInventorySaveOutputPort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class TimeInventorySaveAdapter implements TimeInventorySaveOutputPort {

    private final TimeInventoryRepository timeInventoryRepository;

    private final TimeInventoryOutMapper timeInventoryOutMapper;

    @Transactional
    @Override
    public void save(TimeInventory timeInventory) {

        log.info("Iniciado adaptador para salvar TimeInventory.");

        var timeInventorySaved = Optional.ofNullable(timeInventory)
            .map(this.timeInventoryOutMapper::toTimeInventoryEntity)
            .map(this.timeInventoryRepository::save)
            .orElseThrow();

        log.info("Finalizado adaptador para salvar TimeInventory, com nomeFantasia: {}.", timeInventorySaved.getNomeFantasia());
    }
}

