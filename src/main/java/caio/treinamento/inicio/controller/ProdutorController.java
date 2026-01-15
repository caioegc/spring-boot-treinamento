package caio.treinamento.inicio.controller;

import caio.treinamento.inicio.entity.Produtor;
import caio.treinamento.inicio.mapper.ProducerMapper;
import caio.treinamento.inicio.producer.ProducerPostRequest;
import caio.treinamento.inicio.response.ProducerGetResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/produtor")
public class ProdutorController {

    public static ProducerMapper MAPPER = ProducerMapper.INSTANCE;
    @GetMapping
    public ResponseEntity<List<ProducerGetResponse>> findByAll(@RequestParam(required = false) String nome) {
        var produtors = Produtor.produtorList();

        if (nome == null) {
            var list = MAPPER.listGetResponse(produtors);
            return ResponseEntity.ok(list);
        } else {
            var list2 = MAPPER.listGetResponse(produtors)
                    .stream()
                    .filter(c -> c.getNome().equalsIgnoreCase(nome))
                    .toList();
            return  ResponseEntity.ok(list2);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProducerGetResponse> findByID(@PathVariable("id") Long id) {

        var producerGetResponse = Produtor.produtorList()
                .stream()
                .filter(c -> c.getId().equals(id))
                .map(MAPPER::paraGetResponse)
                .findFirst()
                .orElse(null);

        return ResponseEntity.ok(producerGetResponse);
    }

    @PostMapping()
    public ResponseEntity<ProducerGetResponse> save(@RequestBody ProducerPostRequest producerPostRequest) {
        var produtor = MAPPER.paraProdutor(producerPostRequest);
        var produtor2 = MAPPER.paraGetResponse(produtor);

        Produtor.produtorList().add(produtor);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtor2);

    }


}
