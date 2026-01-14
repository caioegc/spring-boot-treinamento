package caio.treinamento.inicio.controller;

import caio.treinamento.inicio.entity.Heroe;
import caio.treinamento.inicio.producer.HeroePostRequest;
import caio.treinamento.inicio.response.HeroeGetRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

@RestController
@RequestMapping("v1/heroe")
public class HeroeController {



    @GetMapping
    public List<Heroe> findByAll(@RequestParam(required = false) String nome) {
        if (nome == null) {
            return Heroe.heroeList();
        }
        return Heroe.heroeList().stream().filter(c -> c.getNome().equalsIgnoreCase(nome)).toList();
    }

    @GetMapping("/{id}")
    public Heroe findByName(@PathVariable("id") Long id){
        return Heroe.heroeList().stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    @PostMapping
    public ResponseEntity<HeroeGetRequest> save (@RequestBody HeroePostRequest heroePostRequest){
        Heroe build = Heroe.builder()
                .id(ThreadLocalRandom.current().nextLong(1000))
                .nome(heroePostRequest.getNome())
                .atDate(LocalDateTime.now())
                .build();

        HeroeGetRequest build1 = HeroeGetRequest
                .builder()
                .id(build.getId())
                .nome(build.getNome())
                .atDate(build.getAtDate())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(build1);

    }


}
