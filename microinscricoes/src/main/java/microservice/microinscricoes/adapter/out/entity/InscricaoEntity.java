package microservice.microinscricoes.adapter.out.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.time.LocalDate;

@Document(collation = "inscricoes")
public final class InscricaoEntity {

    @Id
    private Long id;

    @Field(name = "torneio_id")
    private Long torneioId;

    @Field(name = "data_inicio")
    private LocalDate dataInicio;

    @Field(name = "data_fim")
    private LocalDate dataFim;

    @Field(name = "valor")
    private BigDecimal valor;

}

