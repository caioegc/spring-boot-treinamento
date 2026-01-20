package caio.treinamento.inicio.repository;

import caio.treinamento.inicio.entity.Produtor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataRepository {
    public final List<Produtor> produtors = new ArrayList<>();


     {
        var mappa = Produtor.builder().id(1L).nome("Mappa").createdAt(LocalDateTime.now()).build();
        var kyotoAnimattion = Produtor.builder().id(2L).nome("Kyoto Animattion").createdAt(LocalDateTime.now()).build();
        var mandhouse = Produtor.builder().id(3L).nome("Mandhouse").createdAt(LocalDateTime.now()).build();
        produtors.addAll(List.of(mappa, kyotoAnimattion, mandhouse));
    }

    public List<Produtor> getProdutors() {
        return produtors;
    }
}
