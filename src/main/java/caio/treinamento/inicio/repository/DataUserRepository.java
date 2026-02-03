package caio.treinamento.inicio.repository;

import caio.treinamento.inicio.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class DataUserRepository {
  private List<User> userList = new ArrayList<>();

    {
        var user1 = User.builder().id(1L).email("dudueocara77@gmail.com").primeiroNome("Caio").ultimoNome("Carvado").build();
        var user2 = User.builder().id(2L).email("dudueocara77@gmail.com").primeiroNome("Caio").ultimoNome("Carvado").build();
        var user3 = User.builder().id(3L).email("dudueocara77@gmail.com").primeiroNome("Caio").ultimoNome("Carvado").build();
        var user4 = User.builder().id(4L).email("dudueocara77@gmail.com").primeiroNome("Caio").ultimoNome("Carvado").build();
        userList.addAll(List.of(user1, user2, user3, user4));
    }

    public List<User> getUser(){
        return userList;
    }


}
