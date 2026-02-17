package caio.treinamento.inicio.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestPut {
    @NotNull(message = "id não pode ficar nulo")
    private Long id;

    @NotBlank(message = "primeiro não pode ficar vazio")
    private String primeiroNome;

    @NotBlank(message = "ultimoNome não pode ficar vazio")
    private String ultimoNome;

    @NotBlank(message = "E-mail não pode ficar vazio")
    @Email(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "E-mail não pode ser invalido")
    private String email;
}

