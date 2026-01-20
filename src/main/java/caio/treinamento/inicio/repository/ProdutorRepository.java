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

    final DataRepository dataRepository;
    private final Conections connections;



    public List<Produtor> produtorList() {
        return dataRepository.getProdutors();

    }

    public List<Produtor> listByNome(String nome){
        log.debug(connections);

       return dataRepository.getProdutors().stream()
                .filter(a -> a.getNome().equalsIgnoreCase(nome))
                .toList();

    }

    public Optional<Produtor> byId(Long id){
        return dataRepository.getProdutors().stream().filter(a -> a.getId().equals(id)).findFirst();


    }

    public Produtor create(Produtor produtor){
        dataRepository.getProdutors().add(produtor);
        return produtor;
    }

    public void delete(Produtor produtor){
        dataRepository.getProdutors().remove(produtor);
    }

    public void update(Produtor produtor){
        delete(produtor);
        create(produtor);
    }
}
