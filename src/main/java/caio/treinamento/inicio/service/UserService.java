package caio.treinamento.inicio.service;

import caio.treinamento.inicio.entity.User;
import caio.treinamento.inicio.repository.DataUserRepository;
import caio.treinamento.inicio.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public List<User> listUser(String nome){
        return nome == null ? userRepository.listAll() : userRepository.listByName(nome);
    }

    public User listById(Long id){
       return userRepository.listId(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void create(User user){
        userRepository.createUser(user);
    }
    public void detete(Long id){
        var user1 = listById(id);
        userRepository.deleteUser(user1);
    }

    public void update(User user){
        var user1 = listById(user.getId());
        userRepository.updateUser(user1);
    }
}
