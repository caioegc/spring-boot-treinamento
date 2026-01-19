package caio.treinamento.inicio.controller;

import caio.treinamento.inicio.entity.Produtor;
import caio.treinamento.inicio.mapper.ProducerMapper;
import caio.treinamento.inicio.producer.HeroePutRequest;
import caio.treinamento.inicio.producer.ProducerPostRequest;
import caio.treinamento.inicio.producer.ProducerPutRequest;
import caio.treinamento.inicio.response.ProducerGetResponse;
import caio.treinamento.inicio.service.ProdutorService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("v1/produtor")
@RequiredArgsConstructor
public class ProdutorController {

    private final ProducerMapper MAPPER;
    private final ProdutorService service;

    @GetMapping
    public ResponseEntity<List<ProducerGetResponse>> findByAll(@RequestParam(required = false) String nome) {

        var produtors = service.produtorList(nome);
        var list = MAPPER.listGetResponse(produtors);
        return ResponseEntity.ok(list);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProducerGetResponse> findByID(@PathVariable("id") Long id) {

        var produtor = service.listById(id);
        var producerGetResponse = MAPPER.paraGetResponse(produtor);
        return ResponseEntity.ok(producerGetResponse);
    }

    @PostMapping()
    public ResponseEntity<ProducerGetResponse> save(@RequestBody ProducerPostRequest producerPostRequest) {
        var produtor = MAPPER.paraProdutor(producerPostRequest);
        var produtor1 = service.create(produtor);
        var produtor2 = MAPPER.paraGetResponse(produtor1);

        return ResponseEntity.status(HttpStatus.CREATED).body(produtor2);

    }

    @PutMapping
    public ResponseEntity<Void> updateById(@RequestBody ProducerPutRequest produtor) {

        var produtor1 = MAPPER.paraProdutor(produtor);

        service.update(produtor1);

        return ResponseEntity.noContent().build();

    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {

        service.delete(id);

        return ResponseEntity.noContent().build();

    }


}
