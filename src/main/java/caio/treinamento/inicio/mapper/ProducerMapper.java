package caio.treinamento.inicio.mapper;

import caio.treinamento.inicio.entity.Produtor;
import caio.treinamento.inicio.producer.ProducerPostRequest;
import caio.treinamento.inicio.response.ProducerGetResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProducerMapper {
    ProducerMapper INSTANCE = Mappers.getMapper(ProducerMapper.class);

    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "id", expression = "java(java.util.concurrent.ThreadLocalRandom.current().nextLong(1000))")
    Produtor paraProdutor(ProducerPostRequest postRequest);

    ProducerGetResponse paraGetResponse(Produtor produtor);


    }

