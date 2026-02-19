package caio.treinamento.inicio.controller;

import caio.treinamento.inicio.entity.Heroe;
import caio.treinamento.inicio.mapper.HeroeMapper;
import caio.treinamento.inicio.response.HeroeGetResponse;
import caio.treinamento.inicio.service.HeroeService;
import caio.treinamento.inicio.request.HeroePostRequest;
import caio.treinamento.inicio.request.HeroePutRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/heroe")
@RequiredArgsConstructor
public class HeroeController {

    private final HeroeMapper MAPPER;
    private final HeroeService heroeService;

    @GetMapping
    public ResponseEntity<List<HeroeGetResponse>> findByAll(@RequestParam(required = false) String nome) {

        var heroe = heroeService.listAll(nome);

        var getAnime = MAPPER.list(heroe);

        return ResponseEntity.ok(getAnime);

    }

    @GetMapping("/page")
    public ResponseEntity<Page<HeroeGetResponse>> findByAll(Pageable page) {

        var heroe = heroeService.listAllPage(page).map(MAPPER::toHeroeGet);

        return ResponseEntity.ok(heroe);

    }

    @GetMapping("/{id}")
    public ResponseEntity<HeroeGetResponse> findById(@PathVariable Long id) {

        var heroe = heroeService.listById(id);

        var heroeGet = MAPPER.toHeroeGet(heroe);

        return ResponseEntity.ok(heroeGet);
    }

    @PostMapping
    public ResponseEntity<HeroeGetResponse> save(@RequestBody @Valid HeroePostRequest heroePostRequest) {


        var heroe = MAPPER.toHeroe(heroePostRequest);

        var heroe1 = heroeService.create(heroe);

        var heroe2 = MAPPER.toHeroeGet(heroe1);

        return ResponseEntity.status(HttpStatus.CREATED).body(heroe2);

    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody @Valid HeroePutRequest heroePutRequest) {


        var toHeroePut = MAPPER.toHeroe(heroePutRequest);
        heroeService.update(toHeroePut);


        return ResponseEntity.noContent().build();


    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {

        heroeService.delete(id);

        return ResponseEntity.noContent().build();


    }

}
