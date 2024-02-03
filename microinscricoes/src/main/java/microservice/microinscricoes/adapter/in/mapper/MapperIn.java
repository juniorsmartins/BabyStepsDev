package microservice.microinscricoes.adapter.in.mapper;

import microservice.microinscricoes.adapter.in.dto.InscricaoIdDto;
import microservice.microinscricoes.adapter.in.dto.request.InscricaoOpenDtoIn;
import microservice.microinscricoes.adapter.in.dto.request.InscritoRegisterDtoIn;
import microservice.microinscricoes.adapter.in.dto.response.InscricaoOpenDtoOut;
import microservice.microinscricoes.adapter.in.dto.response.InscritoRegisterDtoOut;
import microservice.microinscricoes.application.core.domain.Inscricao;
import microservice.microinscricoes.application.core.domain.Inscrito;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface MapperIn {

//    @Mapping(source = "dataInicio", target = "dataInicio", qualifiedByName = "setDataLocalDate")
//    @Mapping(source = "dataFim", target = "dataFim", qualifiedByName = "setDataLocalDate")
    Inscricao toInscricao(InscricaoOpenDtoIn inscricaoOpenDtoIn);

    @Named("setDataLocalDate")
    default LocalDate setDataLocalDate(String data) {

        // Formatador para o padrão da data
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Conversão da String para LocalDate
        return LocalDate.parse(data, formatter);
    }

//    @Mapping(source = "dataInicio", target = "dataInicio", qualifiedByName = "setDataString")
//    @Mapping(source = "dataFim", target = "dataFim", qualifiedByName = "setDataString")
    InscricaoOpenDtoOut toInscricaoOpenDtoOut(Inscricao inscricao);

    @Named("setDataString")
    default String setDataString(LocalDate data) {

        // Formatador para o padrão da data
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Conversão de LocalDate para String
        return data.format(formatter);
    }

    Inscrito toInscrito(InscritoRegisterDtoIn inscritoRegisterDtoIn);

    InscritoRegisterDtoOut toInscritoRegisterDtoOut(Inscrito inscrito);

    Inscricao toInscricao(InscricaoIdDto inscricaoIdDto);

    InscricaoIdDto toInscricaoIdDto(Inscricao inscricao);

    Pagamento toPagamento(PagamentoDto pagamentoDto);

    PagamentoDto toPagamentoDto(Pagamento pagamento);
}

