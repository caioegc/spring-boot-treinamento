package caio.treinamento.inicio.controller;

import caio.treinamento.inicio.entity.Produtor;
import org.springframework.web.bind.annotation.*;

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
        }else{
        return produto.stream().filter(c -> c.getNome().equalsIgnoreCase(nome)).toList();
    }}

    @GetMapping("/{id}")
    public Produtor findByName(@PathVariable("id") Long id){
        return Produtor.produtorList().stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    @PostMapping
    public Produtor save (@RequestBody Produtor produtor){
        produtor.setId(ThreadLocalRandom.current().nextLong(1000));
        Produtor.produtorList().add(produtor);
        return produtor;

    }


}
