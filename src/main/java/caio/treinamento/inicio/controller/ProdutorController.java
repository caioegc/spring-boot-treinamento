package caio.treinamento.inicio.controller;

import caio.treinamento.inicio.entity.Produtor;
import caio.treinamento.inicio.mapper.ProducerMapper;
import caio.treinamento.inicio.producer.ProducerPostRequest;
import caio.treinamento.inicio.response.ProducerGetResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("v1/produtor")
public class ProdutorController {

    public static ProducerMapper MAPPER = ProducerMapper.INSTANCE;
    @GetMapping
    public List<Produtor> findByAll(@RequestParam(required = false) String nome) {
        var produto = Produtor.produtorList();

        if (nome == null) {
            return produto;
        } else {
            return produto.stream().filter(c -> c.getNome().equalsIgnoreCase(nome)).toList();
        }
    }

    @GetMapping("/{id}")
    public Produtor findByID(@PathVariable("id") Long id) {
        return Produtor.produtorList().stream().filter(c -> c.getId().equals(id)).findFirst().orElse(null);
    }

    @PostMapping()
    public ResponseEntity<ProducerGetResponse> save(@RequestBody ProducerPostRequest producerPostRequest) {
        var produtor = MAPPER.paraProdutor(producerPostRequest);
        var produtor2 = MAPPER.paraGetResponse(produtor);

        Produtor.produtorList().add(produtor);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(produtor2);

    }


}
