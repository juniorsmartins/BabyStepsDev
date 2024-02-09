package microservice.microtorneios.adapters.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.microtorneios.adapters.out.repository.TorneioRepository;
import microservice.microtorneios.application.port.output.ExistIdTorneioOutputPort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ExistIdTorneioAdapter implements ExistIdTorneioOutputPort {

    private final TorneioRepository torneioRepository;

    @Transactional(readOnly = true)
    @Override
    public Boolean existIdTorneio(final Long id) {

        return null;
    }
}

