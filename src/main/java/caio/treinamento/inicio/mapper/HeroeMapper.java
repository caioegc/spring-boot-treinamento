package caio.treinamento.inicio.mapper;

import caio.treinamento.inicio.entity.Heroe;
import caio.treinamento.inicio.response.HeroeGetResponse;
import caio.treinamento.inicio.request.HeroePostRequest;
import caio.treinamento.inicio.request.HeroePutRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface HeroeMapper {

    Heroe toHeroe(HeroePostRequest heroePostRequest);

    Heroe toHeroe(HeroePutRequest heroePutRequest);

    HeroeGetResponse toHeroeGet(Heroe heroe);

    List<HeroeGetResponse> list(List<Heroe> heroe);

}
