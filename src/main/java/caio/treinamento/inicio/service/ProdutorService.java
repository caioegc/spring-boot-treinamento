package caio.treinamento.inicio.service;

import caio.treinamento.inicio.entity.Produtor;
import caio.treinamento.inicio.exceptions.NotFoundException;
import caio.treinamento.inicio.repository.ProdutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutorService {
    private final ProdutorRepository repository;

    public List<Produtor> produtorList(String nome) {

        return nome == null ? repository.produtorList() : repository.listByNome(nome);
    }

    public Produtor listById(Long id) {
        return repository.byId(id).orElseThrow(() -> new NotFoundException("Necessário colocar o ID"));
    }

    public Produtor create(Produtor produtor) {
        return repository.create(produtor);
    }

    public void delete(Long id) {
        var produtor1 = listById(id);
        repository.delete(produtor1);
    }

    public void update(Produtor produtor) {
        var produtor1 = listById(produtor.getId());
        produtor.setCreatedAt(produtor1.getCreatedAt());
        repository.update(produtor);
    }
}
