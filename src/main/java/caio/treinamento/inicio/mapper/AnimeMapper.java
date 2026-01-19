package caio.treinamento.inicio.mapper;


import caio.treinamento.inicio.entity.Anime;
import caio.treinamento.inicio.producer.AnimePostRequest;
import caio.treinamento.inicio.producer.AnimePuttRequest;
import caio.treinamento.inicio.response.AnimeGetResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AnimeMapper {
    AnimeMapper INSTANCE = Mappers.getMapper(AnimeMapper.class);

    @Mapping(target = ("id"), expression = "java(java.util.concurrent.ThreadLocalRandom.current().nextLong(1000))")
    Anime animePostRequest(AnimePostRequest animePostRequest);

    AnimeGetResponse animeGetResponse(Anime anime);

    Anime animePutRequest(AnimePuttRequest animePuttRequest);
    List<Anime> list (List<Anime> anime);
}
