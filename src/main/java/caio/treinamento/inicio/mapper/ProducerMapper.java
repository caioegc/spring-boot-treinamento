package caio.treinamento.inicio.mapper;

import caio.treinamento.inicio.entity.Produtor;
import caio.treinamento.inicio.producer.ProducerPostRequest;
import caio.treinamento.inicio.producer.ProducerPutRequest;
import caio.treinamento.inicio.response.ProducerGetResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProducerMapper {

    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "id", expression = "java(java.util.concurrent.ThreadLocalRandom.current().nextLong(1000))")
    Produtor paraProdutor(ProducerPostRequest postRequest);

    ProducerGetResponse paraGetResponse(Produtor produtor);

    List<ProducerGetResponse> listGetResponse(List<Produtor> produtors);


    Produtor paraProdutor(ProducerPutRequest producerPutRequest);
}

