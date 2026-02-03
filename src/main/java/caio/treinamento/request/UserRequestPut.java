package caio.treinamento.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestPut {
    private Long id;
    private String primeiroNome;
    private String ultimoNome;
    private String email;
}

