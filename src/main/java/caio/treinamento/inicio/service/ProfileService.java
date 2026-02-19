package caio.treinamento.inicio.service;

import caio.treinamento.inicio.entity.Profile;
import caio.treinamento.inicio.repository.ProfileRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository repository;

    public List<Profile> findAll(String nome){
        return (nome == null)? repository.findAll() : repository.findByNome(nome);
    }

    public Profile findById(Long id){
        return repository.findById(id).orElseThrow();
    }

    public Profile save(Profile profile){
       return repository.save(profile);
    }

    public void delete(Long id){
        var byId = findById(id);
        repository.delete(byId);
    }

    public void update(Profile profile){
        findById(profile.getId());
        repository.save(profile);
    }
}
