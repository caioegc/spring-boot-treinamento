package caio.treinamento.inicio.entity;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Produtor {
  @EqualsAndHashCode.Include
  private Long id;
  private String nome;
  private LocalDateTime createdAt;


}
