package caio.treinamento.inicio.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class User {

  private Long id;
  private String primeiroNome;
  private String ultimoNome;
  private String email;
}
