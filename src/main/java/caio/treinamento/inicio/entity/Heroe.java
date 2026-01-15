package caio.treinamento.inicio.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class Heroe {
  private Long id;
  private String nome;
  private LocalDateTime atDate;
  public static List<Heroe> list = new ArrayList<>();

  static {
    var naruto = builder().id(1L).nome("Naruto").atDate(LocalDateTime.now()).build();
    var dragonball = builder().id(2L).nome("Goku").atDate(LocalDateTime.now()).build();
    var onePiece = builder().id(3L).nome("Luffy").atDate(LocalDateTime.now()).build();
    list.addAll(List.of(naruto, dragonball, onePiece));
  }


    public static List<Heroe> heroeList() {
      return list;

    }

}
