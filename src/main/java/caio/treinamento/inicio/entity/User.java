package caio.treinamento.inicio.entity;

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
