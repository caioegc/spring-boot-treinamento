package caio.treinamento.inicio.controller;

import caio.treinamento.inicio.entity.Heroe;
import caio.treinamento.inicio.mapper.ProducerMapperHeroe;
import caio.treinamento.inicio.producer.HeroePostRequest;
import caio.treinamento.inicio.producer.HeroePutRequest;
import caio.treinamento.inicio.response.HeroeGetResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("v1/heroe")
public class HeroeController {

    ProducerMapperHeroe MAPPER = ProducerMapperHeroe.INSTANCE;

    @GetMapping
    public ResponseEntity<List<HeroeGetResponse>> findByAll(@RequestParam(required = false) String nome) {

        var heroe = Heroe.heroeList();
        var getAnime = MAPPER.list(heroe);
        if (nome == null) {
            return ResponseEntity.ok(getAnime);
        }
       var list = getAnime.stream().filter(c -> c.getNome().equalsIgnoreCase(nome)).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HeroeGetResponse> findByName(@PathVariable("id") Long id){
        var heroeGetRequest = Heroe.heroeList()
                .stream()
                .filter(c -> c.getId() == id)
                .map(MAPPER::toHeroeGet)
                .findFirst()
                .orElse(null);

        return ResponseEntity.ok(heroeGetRequest);
    }

    @PostMapping
    public ResponseEntity<HeroeGetResponse> save (@RequestBody HeroePostRequest heroePostRequest){

        var heroe = MAPPER.toHeroe(heroePostRequest);
        var heroe2 = MAPPER.toHeroeGet(heroe);

        Heroe.heroeList().add(heroe);

        return ResponseEntity.status(HttpStatus.CREATED).body(heroe2);

    }

    @PutMapping
    public ResponseEntity<Void> rename(@RequestBody HeroePutRequest heroePutRequest){

        var heroeRemove = Heroe.heroeList()
                .stream()
                .filter(c -> c.getId().equals(heroePutRequest.getId()))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var heroePut = MAPPER.toHeroe(heroePutRequest);
        Heroe.heroeList().remove(heroeRemove);
        Heroe.heroeList().add(heroePut);

        return ResponseEntity.noContent().build();


    }

    @DeleteMapping
    public ResponseEntity<Void> deleteById(@PathVariable Long id){

        var heroe = Heroe.heroeList()
                .stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

            Heroe.heroeList().remove(heroe);

            return ResponseEntity.noContent().build();


    }


}
