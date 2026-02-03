package caio.treinamento.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserRequest {
    private String primeiroNome;
    private String ultimoNome;
    private String email;
}


