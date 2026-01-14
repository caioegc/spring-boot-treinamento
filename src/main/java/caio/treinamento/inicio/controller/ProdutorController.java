package caio.treinamento.inicio.controller;

import caio.treinamento.inicio.entity.Produtor;
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
        var build = Produtor.builder()
                .id(ThreadLocalRandom.current().nextLong(1000))
                .nome(producerPostRequest.getNome())
                .createdAt(LocalDateTime.now())
                .build();

        Produtor.produtorList().add(build);

        var build1 = ProducerGetResponse.builder()
                .id(build.getId())
                .nome(build.getNome())
                .createdAt(build.getCreatedAt())
                .build();

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(build1);

    }


}
