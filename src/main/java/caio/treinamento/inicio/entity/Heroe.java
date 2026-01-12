package caio.treinamento.inicio.entity;

import java.util.ArrayList;
import java.util.List;

public class Heroe {
  private long id;
  private String nome;


    public Heroe(long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

  public long getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }
}
