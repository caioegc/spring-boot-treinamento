package caio.treinamento.inicio.controller;

import caio.treinamento.inicio.mapper.ProducerMapper;

import caio.treinamento.inicio.response.ProducerGetResponse;
import caio.treinamento.inicio.service.ProdutorService;
import caio.treinamento.inicio.request.ProducerPostRequest;
import caio.treinamento.inicio.request.ProducerPutRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/produtor")
@RequiredArgsConstructor
public class ProdutorController {

    private final ProducerMapper mapper;
    private final ProdutorService service;

    @GetMapping
    public ResponseEntity<List<ProducerGetResponse>> findByAll(@RequestParam(required = false) String nome) {

        var produtors = service.produtorList(nome);
        var list = mapper.listGetResponse(produtors);
        return ResponseEntity.ok(list);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProducerGetResponse> findByID(@PathVariable("id") Long id) {

        var produtor = service.listById(id);
        var producerGetResponse = mapper.paraGetResponse(produtor);
        return ResponseEntity.ok(producerGetResponse);
    }

    @PostMapping()
    public ResponseEntity<ProducerGetResponse> save(@RequestBody ProducerPostRequest producerPostRequest) {
        var produtor = mapper.paraProdutor(producerPostRequest);
        var produtor1 = service.create(produtor);
        var produtor2 = mapper.paraGetResponse(produtor1);

        return ResponseEntity.status(HttpStatus.CREATED).body(produtor2);

    }

    @PutMapping
    public ResponseEntity<Void> updateById(@RequestBody ProducerPutRequest produtor) {

        var produtor1 = mapper.paraProdutor(produtor);

        service.update(produtor1);

        return ResponseEntity.noContent().build();

    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {

        service.delete(id);

        return ResponseEntity.noContent().build();

    }


}
