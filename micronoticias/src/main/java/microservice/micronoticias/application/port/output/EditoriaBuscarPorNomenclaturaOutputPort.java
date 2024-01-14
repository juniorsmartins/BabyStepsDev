package microservice.micronoticias.application.port.output;

import microservice.micronoticias.application.core.domain.Editoria;

import java.util.Optional;

public interface EditoriaBuscarPorNomenclaturaOutputPort {

    Optional<Editoria> buscarPorNomenclatura(String nomenclatura);
}

