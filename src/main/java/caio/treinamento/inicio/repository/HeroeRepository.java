package caio.treinamento.inicio.repository;

import caio.treinamento.inicio.entity.Heroe;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class HeroeRepository {
    public static final List<Heroe> HEROES = new ArrayList<>();

    static {
        var naruto = Heroe.builder().id(1L).nome("Naruto").atDate(LocalDateTime.now()).build();
        var dragonball = Heroe.builder().id(2L).nome("Goku").atDate(LocalDateTime.now()).build();
        var onePiece = Heroe.builder().id(3L).nome("Luffy").atDate(LocalDateTime.now()).build();
        HEROES.addAll(List.of(naruto, dragonball, onePiece));
    }


    public List<Heroe> listAll() {
        return HEROES;

    }

    public List<Heroe> listByName(String nome) {
        return HEROES.stream()
                .filter(a-> a.getNome().equalsIgnoreCase(nome))
                .toList();

    }
    public Optional<Heroe> listById(Long id){
       return HEROES.stream().filter(a-> a.getId().equals(id)).findFirst();
    }

    public Heroe createHeroe(Heroe heroe){
         HEROES.add(heroe);
         return heroe;
    }

    public void deleteById(Heroe heroe){
         HEROES.remove(heroe);
    }

    public void update(Heroe heroe){
        deleteById(heroe);
        createHeroe(heroe);
    }

}
