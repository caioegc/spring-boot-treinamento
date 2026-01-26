package caio.treinamento.inicio.repository;

import caio.treinamento.inicio.entity.Heroe;
import caio.treinamento.inicio.entity.Produtor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataRepository {
    public final List<Produtor> produtors = new ArrayList<>();
    public final List<Heroe> HEROES = new ArrayList<>();

    {
        var mappa = Produtor.builder().id(1L).nome("Mappa").createdAt(LocalDateTime.now()).build();
        var kyotoAnimattion = Produtor.builder().id(2L).nome("Kyoto Animattion").createdAt(LocalDateTime.now()).build();
        var mandhouse = Produtor.builder().id(3L).nome("Mandhouse").createdAt(LocalDateTime.now()).build();
        produtors.addAll(List.of(mappa, kyotoAnimattion, mandhouse));
    }
    {
        var naruto = Heroe.builder().id(1L).nome("Naruto").atDate(LocalDateTime.now()).build();
        var dragonball = Heroe.builder().id(2L).nome("Goku").atDate(LocalDateTime.now()).build();
        var onePiece = Heroe.builder().id(3L).nome("Luffy").atDate(LocalDateTime.now()).build();
        HEROES.addAll(List.of(naruto, dragonball, onePiece));
    }


    public List<Produtor> getProdutors() {
        return produtors;
    }

    public List<Heroe> getHEROES() {
        return HEROES;
    }
}
