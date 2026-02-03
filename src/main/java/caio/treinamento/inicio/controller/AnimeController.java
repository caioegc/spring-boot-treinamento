package caio.treinamento.inicio.controller;


import caio.treinamento.inicio.entity.Anime;
import caio.treinamento.inicio.mapper.AnimeMapper;
import caio.treinamento.inicio.producer.AnimePostRequest;
import caio.treinamento.inicio.producer.AnimePuttRequest;
import caio.treinamento.inicio.response.AnimeGetResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("v1/anime")
public class AnimeController {

    AnimeMapper MAPPER = AnimeMapper.INSTANCE;

    @GetMapping
    public ResponseEntity<List<Anime>> listAll(@RequestParam String nome) {
        var list = Anime.getList();
        var list1 = MAPPER.list(list);

        if (nome == null) {
            return ResponseEntity.ok(list1);
        }

        var list2 = Anime.getList().stream().filter(a -> a.getNome().equalsIgnoreCase(nome)).toList();
        return ResponseEntity.ok(list2);
    }

    @GetMapping("{id}")
    public ResponseEntity<AnimeGetResponse> listAll(@PathVariable Long id) {
        var anime1 = Anime.getList().stream()
                .filter(a -> a.getId().equals(id))
                .map(MAPPER::animeGetResponse)
                .findFirst()
                .orElse(null);

        return ResponseEntity.ok(anime1);
    }

    @PostMapping
    public ResponseEntity<AnimeGetResponse> create(@RequestBody AnimePostRequest anime) {
        var anime1 = MAPPER.animePostRequest(anime);
        var anime2 = MAPPER.animeGetResponse(anime1);

        Anime.getList().add(anime1);
        return ResponseEntity.status(HttpStatus.CREATED).body(anime2);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody AnimePuttRequest animePuttRequest) {
        var animeDelete = Anime.getList().stream()
                .filter(a -> a.getId().equals(animePuttRequest.getId()))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var anime = MAPPER.animePutRequest(animePuttRequest);

        Anime.getList().remove(animeDelete);
        Anime.getList().add(anime);

        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        var animeDelete = Anime.getList().stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Anime.getList().remove(animeDelete);

        return ResponseEntity.noContent().build();


    }

}