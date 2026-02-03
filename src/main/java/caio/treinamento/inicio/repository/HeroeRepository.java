package caio.treinamento.inicio.repository;

import caio.treinamento.inicio.entity.Heroe;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class HeroeRepository {
    private final DataRepository dataRepository;


    public List<Heroe> listAll() {
        return dataRepository.getHEROES();

    }

    public List<Heroe> listByName(String nome) {
        return dataRepository.getHEROES().stream()
                .filter(a -> a.getNome().equalsIgnoreCase(nome))
                .toList();

    }

    public Optional<Heroe> listById(Long id) {
        return dataRepository.getHEROES().stream().filter(a -> a.getId().equals(id)).findFirst();
    }

    public Heroe createHeroe(Heroe heroe) {
        dataRepository.getHEROES().add(heroe);
        return heroe;
    }

    public void deleteById(Heroe heroe) {
        dataRepository.getHEROES().remove(heroe);
    }

    public void update(Heroe heroe) {
        deleteById(heroe);
        createHeroe(heroe);
    }

}
