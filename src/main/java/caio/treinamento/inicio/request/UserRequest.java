package caio.treinamento.inicio.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserRequest {

    @NotBlank(message = "O primeiro nome não pode ser nulo e precisa ter pelo menos 1 caracter")
    private String primeiroNome;
    @NotBlank(message = " O segundo nome não pode ser nulo e precisa ter pelo menos 1 caracter")
    private String ultimoNome;
    @NotBlank(message = "O email não pode ser nulo e precisa ter pelo menos 1 caracter")
    @Email(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "O e-mail não é valido")
    private String email;
}


