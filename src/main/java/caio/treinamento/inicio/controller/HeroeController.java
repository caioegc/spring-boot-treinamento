package caio.treinamento.inicio.controller;

import caio.treinamento.inicio.entity.Heroe;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("v1/heroe")
public class HeroeController {

    Heroe heroe;

   List<Heroe> heroes = new ArrayList<>(List.of(
           new Heroe(15L , "Naruto"),
           new Heroe(1L , "Goku"),
           new Heroe(12L , "Luffy")));


    @GetMapping
    public List<Heroe> findByAll(){
        return heroes;
    }

    @GetMapping("filtro")
    public List<Heroe> findByName(@RequestParam("nome") String nome){
        return heroes.stream().filter(c -> c.getNome().equalsIgnoreCase(nome)).toList();
    }

    @GetMapping("/{id}")
    public Heroe findByName(@PathVariable("id") Long id){
        return heroes.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

}
