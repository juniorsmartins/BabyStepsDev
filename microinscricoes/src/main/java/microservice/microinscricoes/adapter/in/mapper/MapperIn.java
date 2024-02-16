package microservice.microinscricoes.adapter.in.mapper;

import microservice.microinscricoes.adapter.in.dto.InscricaoIdDto;
import microservice.microinscricoes.adapter.in.dto.TimeIdDto;
import microservice.microinscricoes.adapter.in.dto.TorneioIdDto;
import microservice.microinscricoes.adapter.in.dto.request.InscricaoOpenDtoIn;
import microservice.microinscricoes.adapter.in.dto.request.InscritoRegisterDtoIn;
import microservice.microinscricoes.adapter.in.dto.request.TimeSaveDto;
import microservice.microinscricoes.adapter.in.dto.request.TorneioSaveDto;
import microservice.microinscricoes.adapter.in.dto.response.InscricaoOpenDtoOut;
import microservice.microinscricoes.adapter.in.dto.response.InscritoRegisterDtoOut;
import microservice.microinscricoes.application.core.domain.Inscricao;
import microservice.microinscricoes.application.core.domain.Inscrito;
import microservice.microinscricoes.application.core.domain.Time;
import microservice.microinscricoes.application.core.domain.Torneio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface MapperIn {

    @Mapping(source = "dataInicio", target = "dataInicio", qualifiedByName = "setDataStringParaLocalDate")
//    @Mapping(source = "dataFim", target = "dataFim", qualifiedByName = "setDataStringParaLocalDate")
    Inscricao toInscricao(InscricaoOpenDtoIn inscricaoOpenDtoIn);

    @Named("setDataStringParaLocalDate")
    default LocalDate setDataStringParaLocalDate(String data) {

        // Formatador para o padrão da data
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Conversão da String para LocalDate
        return LocalDate.parse(data, formatter);
    }

    @Mapping(source = "dataInicio", target = "dataInicio", qualifiedByName = "setDataString")
    @Mapping(source = "dataFim", target = "dataFim", qualifiedByName = "setDataString")
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

    Torneio toTorneio(TorneioIdDto torneioIdDto);

    TorneioIdDto toTorneioIdDto(Torneio torneio);

    Torneio toTorneio(TorneioSaveDto torneioSaveDto);

    Time toTime(TimeSaveDto timeSaveDto);

    Time toTime(TimeIdDto timeIdDto);

    TimeIdDto toTimeIdDto(Time time);
}

