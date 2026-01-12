package caio.treinamento.inicio.entity;

import java.util.ArrayList;
import java.util.List;


public class Produtor {
  private long id;
  private String nome;
  public static List<Produtor> list = new ArrayList<>();

  static {
    var mappa = new Produtor(1L, "Mappa");
    var kyotoAnimattion = new Produtor(2L, "Kyoto Animattion");
    var mandhouse = new Produtor(3L, "Mandhouse");
    list.addAll(List.of(mappa, kyotoAnimattion, mandhouse));
  }


    public Produtor(long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public static List<Produtor> produtorList() {
      return list;

    }
  public long getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public void setId(long id) {
    this.id = id;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }
}
