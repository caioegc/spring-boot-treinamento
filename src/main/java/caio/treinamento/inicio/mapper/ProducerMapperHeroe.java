package caio.treinamento.inicio.mapper;

import caio.treinamento.inicio.entity.Heroe;
import caio.treinamento.inicio.producer.HeroePostRequest;
import caio.treinamento.inicio.response.HeroeGetResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProducerMapperHeroe {
    ProducerMapperHeroe INSTANCE = Mappers.getMapper(ProducerMapperHeroe.class);

    @Mapping(target = "id", expression = "java(java.util.concurrent.ThreadLocalRandom.current().nextLong(1000))")
    @Mapping(target = "atDate", expression = "java(java.time.LocalDateTime.now())")
    Heroe toHeroe(HeroePostRequest heroePostRequest);


    HeroeGetResponse toHeroeGet(Heroe heroe);

    List<HeroeGetResponse> list(List<Heroe> heroe);

}
