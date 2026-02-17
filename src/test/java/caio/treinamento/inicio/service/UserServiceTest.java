package caio.treinamento.inicio.service;

import caio.treinamento.inicio.entity.User;
import caio.treinamento.inicio.repository.UserRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    public UserService userService;

    @Mock
    public UserRepo repository;

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
    void listAllIfNull(){
        BDDMockito.when(repository.findAll()).thenReturn(userList);

        var users = userService.listUser(null);

        Assertions.assertThat(users).isNotNull().hasSize(userList.size());
    }


    @Test
    void listIfNameIsExist(){
        var user = userList.get(0);

        BDDMockito.when(repository.findByPrimeiroNome(user.getPrimeiroNome())).thenReturn(userList);
        var users = userService.listUser(user.getPrimeiroNome());

        Assertions.assertThat(users).isNotNull().hasSize(userList.size()).containsAll(userList);

    }

    @Test
    void shouldReturnUserWhenIdExists(){
        var user = userList.get(0);

        BDDMockito.when(repository.findById(user.getId())).thenReturn(Optional.of(user));

        var user1 = userService.listById(user.getId());

        Assertions.assertThat(user1).isEqualTo(user);
    }

    @Test
    void shouldReturunUserWgenIdNoExists(){
        var user = 155555L;

        BDDMockito.when(repository.findById(user)).thenReturn(Optional.empty());


        Assertions.assertThatException().isThrownBy(() -> userService.listById(user)).isInstanceOf(ResponseStatusException.class);


    }

    @Test
    void createUser(){
        var userCreate = User.builder().id(55L).email("CuiabaDoidao@gmail.com").primeiroNome("Reinaldo").ultimoNome("KingNaldo").build();

        BDDMockito.when(repository.save(userCreate)).thenReturn(userCreate);

        var user = userService.create(userCreate);
        Assertions.assertThat(user).isEqualTo(userCreate).hasNoNullFieldsOrProperties();
    }

    @Test
    void deleteUser(){
        var user = userList.get(0);
        BDDMockito.when(repository.findById(user.getId())).thenReturn(Optional.of(user));

        Assertions.assertThatNoException().isThrownBy(() -> userService.detete(user.getId()));
        BDDMockito.verify(repository).findById(user.getId());
        BDDMockito.verify(repository).delete(user);
    }


    @Test
    void updateUser(){
        var user = userList.get(0);
        user.setPrimeiroNome("Ana Banana");
        BDDMockito.when(repository.findById(user.getId())).thenReturn(Optional.of(user));

        Assertions.assertThatNoException().isThrownBy(() -> userService.update(user));
    }

}