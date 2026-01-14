package caio.treinamento.inicio.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Builder
public class Produtor {
  private Long id;
  private String nome;
  private LocalDateTime createdAt;
  public static List<Produtor> list = new ArrayList<>();

  static {
    var mappa = Produtor.builder().id(1L).nome("Mappa").createdAt(LocalDateTime.now()).build();
    var kyotoAnimattion = Produtor.builder().id(2L).nome("Kyoto Animattion").createdAt(LocalDateTime.now()).build();
    var mandhouse = Produtor.builder().id(3L).nome("Mandhouse").createdAt(LocalDateTime.now()).build();
    list.addAll(List.of(mappa, kyotoAnimattion, mandhouse));
  }

    public static List<Produtor> produtorList() {
      return list;

    }

}
