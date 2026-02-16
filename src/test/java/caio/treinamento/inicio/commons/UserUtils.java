package caio.treinamento.inicio.commons;

import caio.treinamento.inicio.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserUtils {

    public List<User> getList(){
        var user1 = User.builder().id(1L).email("dudueocara77@gmail.com").primeiroNome("Caio").ultimoNome("Carvalho").build();
        var user2 = User.builder().id(2L).email("dudueocara77@gmail.com").primeiroNome("Andre").ultimoNome("Carvalho").build();
        var user3 = User.builder().id(3L).email("dudueocara77@gmail.com").primeiroNome("Eliene").ultimoNome("Carvalho").build();
        var user4 = User.builder().id(4L).email("dudueocara77@gmail.com").primeiroNome("Rafael").ultimoNome("Carvalho").build();
        return new ArrayList<>(List.of(user1, user2, user3, user4));
    }
}
