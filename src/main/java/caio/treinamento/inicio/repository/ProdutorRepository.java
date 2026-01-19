package caio.treinamento.inicio.repository;

import caio.treinamento.inicio.entity.Produtor;
import external.dependency.Conections;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Log4j2
public class ProdutorRepository {

    public static final List<Produtor> PRODUTORS = new ArrayList<>();
    private final Conections connections;

    static {
        var mappa = Produtor.builder().id(1L).nome("Mappa").createdAt(LocalDateTime.now()).build();
        var kyotoAnimattion = Produtor.builder().id(2L).nome("Kyoto Animattion").createdAt(LocalDateTime.now()).build();
        var mandhouse = Produtor.builder().id(3L).nome("Mandhouse").createdAt(LocalDateTime.now()).build();
        PRODUTORS.addAll(List.of(mappa, kyotoAnimattion, mandhouse));
    }

    public List<Produtor> produtorList() {
        return PRODUTORS;

    }

    public List<Produtor> listByNome(String nome){
        log.debug(connections);

       return PRODUTORS.stream()
                .filter(a -> a.getNome().equalsIgnoreCase(nome))
                .toList();

    }

    public Optional<Produtor> byId(Long id){
        return PRODUTORS.stream().filter(a -> a.getId().equals(id)).findFirst();


    }

    public Produtor create(Produtor produtor){
        PRODUTORS.add(produtor);
        return produtor;
    }

    public void delete(Produtor produtor){
        PRODUTORS.remove(produtor);
    }

    public void update(Produtor produtor){
        delete(produtor);
        create(produtor);
    }
}
