package caio.treinamento.inicio.service;

import caio.treinamento.inicio.entity.Heroe;
import caio.treinamento.inicio.exceptions.NotFoundException;
import caio.treinamento.inicio.repository.HeroeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
@RequiredArgsConstructor
public class HeroeService {
    private final HeroeRepository heroeRepository;

    public List<Heroe> listAll(String nome) {
        return nome == null ? heroeRepository.listAll() : heroeRepository.listByName(nome);
    }

    public Heroe listById(Long id) {
        return heroeRepository.listById(id).orElseThrow(() -> new NotFoundException("Necessario id"));
    }

    public Heroe create(Heroe heroe) {
        return heroeRepository.createHeroe(heroe);
    }

    public void delete(Long id) {
        var byId = listById(id);
        heroeRepository.deleteById(byId);
    }

    public void update(Heroe heroe) {
        listById(heroe.getId());
        heroe.setAtDate(heroe.getAtDate());
        heroeRepository.update(heroe);

    }
}
