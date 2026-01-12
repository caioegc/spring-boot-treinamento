package caio.treinamento.inicio.entity;

import java.util.ArrayList;
import java.util.List;


public class Produtor {
  private long id;
  private String nome;
  public static List<Produtor> list = new ArrayList<>();

  static {
    var naruto = new Produtor(1L, "Naruto");
    var dragonball = new Produtor(2L, "Goku");
    var onePiece = new Produtor(3L, "Luffy");
    list.addAll(List.of(naruto, dragonball, onePiece));
  }


    public Produtor(long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public static List<Produtor> heroeList() {
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
