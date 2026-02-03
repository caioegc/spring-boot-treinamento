package caio.treinamento.inicio.repository;

import caio.treinamento.inicio.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final DataUserRepository dataUserRepository;

    public List<User> listAll(){
       return dataUserRepository.getUser();
    }

    public List<User> listByName(String primeiroNome){
        return dataUserRepository.getUser()
                .stream()
                .filter(a -> a.getPrimeiroNome().equalsIgnoreCase(primeiroNome))
                .toList();
    }

    public Optional<User> listId(Long id){
        return dataUserRepository.getUser()
                .stream()
                .filter(a -> a.getId().equals(id))
                .findFirst();

    }

    public void createUser(User user){
        dataUserRepository.getUser().add(user);

    }

    public void deleteUser(User user){
        dataUserRepository.getUser().remove(user);
    }

    public void updateUser(User user){
        createUser(user);
        deleteUser(user);
    }
}
