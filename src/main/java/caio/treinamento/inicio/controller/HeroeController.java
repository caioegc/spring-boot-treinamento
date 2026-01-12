package caio.treinamento.inicio.controller;

import caio.treinamento.inicio.entity.Heroe;
import org.springframework.web.bind.annotation.*;

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
    public Heroe save (@RequestBody Heroe heroe){
        heroe.setId(ThreadLocalRandom.current().nextLong(1000));
        Heroe.heroeList().add(heroe);
        return heroe;

    }


}
