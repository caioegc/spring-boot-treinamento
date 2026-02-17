package caio.treinamento.inicio.service;

import caio.treinamento.inicio.entity.User;
import caio.treinamento.inicio.exceptions.NotFoundException;
import caio.treinamento.inicio.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo repository;

    public List<User> listUser(String nome){
        return nome == null ? repository.findAll() : repository.findByPrimeiroNome(nome);
    }

    public User listById(Long id){
       return repository.findById(id).orElseThrow(() -> new NotFoundException("Necessário ID"));
    }

    public User create(User user){
        validarEmail(user.getEmail());
        repository.save(user);
        return user;
    }
    public void detete(Long id){
        var user1 = listById(id);
        repository.delete(user1);
    }

    public void update(User user){
        var user1 = listById(user.getId());
        repository.save(user1);
    }

    public void validarEmail(String email){
        repository.findByEmail(email).ifPresent(user -> {throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email já existe");});
    }
}
