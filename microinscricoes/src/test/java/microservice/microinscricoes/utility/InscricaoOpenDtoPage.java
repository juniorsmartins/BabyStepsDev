package microservice.microinscricoes.utility;

import microservice.microinscricoes.adapter.in.dto.response.InscricaoOpenDtoOut;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class InscricaoOpenDtoPage extends PageImpl<InscricaoOpenDtoOut> {

    public InscricaoOpenDtoPage(List<InscricaoOpenDtoOut> content, Pageable pageable, long totalElements) {
        super(content, pageable, totalElements);
    }

    public InscricaoOpenDtoPage(List<InscricaoOpenDtoOut> content) {
        super(content);
    }
}

