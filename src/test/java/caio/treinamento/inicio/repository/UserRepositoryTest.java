package caio.treinamento.inicio.repository;

import caio.treinamento.inicio.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class UserRepositoryTest {

    @InjectMocks
    private UserRepository userRepository;

    @Mock
    private DataUserRepository dataUserRepository;

    public List<User> userList = new ArrayList<>();

    @BeforeEach
    void init(){
        {
            var user1 = User.builder().id(1L).email("dudueocara77@gmail.com").primeiroNome("Caio").ultimoNome("Carvalho").build();
            var user2 = User.builder().id(2L).email("dudueocara77@gmail.com").primeiroNome("Andre").ultimoNome("Carvalho").build();
            var user3 = User.builder().id(3L).email("dudueocara77@gmail.com").primeiroNome("Eliene").ultimoNome("Carvalho").build();
            var user4 = User.builder().id(4L).email("dudueocara77@gmail.com").primeiroNome("Rafael").ultimoNome("Carvalho").build();
            userList.addAll(List.of(user1, user2, user3, user4));
        }
    }

    @Test
    void listAll() {
        BDDMockito.when(dataUserRepository.getUser()).thenReturn(userList);

        var users = userRepository.listAll();

        Assertions.assertThat(users).isNotNull().hasSize(userList.size()).isEqualTo(userList);

    }

    @Test
    void listByNameIsNotNull() {
        BDDMockito.when(dataUserRepository.getUser()).thenReturn(userList);
        var user = dataUserRepository.getUser().get(0);
        var users = userRepository.listByName(user.getPrimeiroNome());
        Assertions.assertThat(users)
                .isNotNull()
                .isNotEmpty()
                .allMatch(a -> a.getPrimeiroNome().equalsIgnoreCase(user.getPrimeiroNome()));
    }

    @Test
    void listByNameIsNull() {
        BDDMockito.when(dataUserRepository.getUser()).thenReturn(userList);
        var users2 = userRepository.listByName(null);
        Assertions.assertThat(users2).isEmpty();
    }


    @Test
    void listId_WhenIdExists_ReturnUser() {
        BDDMockito.when(dataUserRepository.getUser()).thenReturn(userList);
        var user = userList.get(0);
        var user1 = userRepository.listId(user.getId());

        Assertions.assertThat(user1).isPresent().contains(user);
    }

    @Test
    void listId_WhenIdNoExists_ReturnEmpty() {
        BDDMockito.when(dataUserRepository.getUser()).thenReturn(userList);
        var user1 = userRepository.listId(null);

        Assertions.assertThat(user1).isEmpty();
    }

    @Test
    void listId_WhenIdFalse_ReturnEmpty() {
        BDDMockito.when(dataUserRepository.getUser()).thenReturn(userList);
        var user1 = userRepository.listId(256l);

        Assertions.assertThat(user1).isEmpty();
    }

    @Test
    void createUser() {
        var initialUser = userList.size();
        BDDMockito.when(dataUserRepository.getUser()).thenReturn(userList);
        var userCreate = User.builder().id(55L).primeiroNome("Antonio").ultimoNome("Fagundes").email("fagundeseocara@gmail.com").build();
        var user = userRepository.createUser(userCreate);

        Assertions.assertThat(user).isEqualTo(userCreate).hasNoNullFieldsOrProperties();
        Assertions.assertThat(userList).hasSize(initialUser + 1).contains(userCreate);

}
    @Test
    void deleteUser() {
        var initialUser = userList.size();
        BDDMockito.when(dataUserRepository.getUser()).thenReturn(userList);

        var user = userList.get(0);
        userRepository.deleteUser(user);
        var users = userRepository.listAll();
        Assertions.assertThat(users).isNotNull().doesNotContain(user).hasSize(initialUser - 1);;

    }

    @Test
    void updateUser() {

        BDDMockito.when(dataUserRepository.getUser()).thenReturn(userList);


        var user = userList.get(0);
        user.setPrimeiroNome("Fernando");

        userRepository.updateUser(userList.get(0));
        var user1 = userRepository.listId(user.getId());

        Assertions.assertThat(user1.get().getPrimeiroNome()).isEqualTo(user.getPrimeiroNome());
    }
}