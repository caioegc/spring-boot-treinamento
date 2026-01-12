package caio.treinamento.inicio.controller;


import caio.treinamento.inicio.entity.Heroe;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("v1/anime")
public class AnimeController {

    List<String> animeList = new ArrayList<>(List.of("Dragon Ball", "Naruto", "One Piece"));

    @GetMapping
    public List<String> findAll() {
        return animeList;
    }


    @GetMapping("filter")
    public List<String> findName(@RequestParam("nome") String nome) {
        return animeList.stream().filter(n -> n.equalsIgnoreCase(nome)).toList();

    }

    @GetMapping("{nome}")
    public String findByName(@PathVariable String nome) {
        return animeList
                .stream()
                .filter(n -> n.equalsIgnoreCase(nome))
                .findFirst()
                .orElse(null);

    }

    @PostMapping
    public void create(@RequestBody String nome) {
        animeList.add(nome);

    }
}