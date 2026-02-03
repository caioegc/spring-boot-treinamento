package caio.treinamento.inicio.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class User {
  private Long id;
  private String primeiroNome;
  private String ultimoNome;
  private String email;

}
