package caio.treinamento.inicio.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class Anime {
    private Long id;
    private String nome;
    private static List<Anime> listAnime = new ArrayList<>();

    static{
        var naruto = builder().id(1L).nome("Naruto").build();
        var dragonBall = builder().id(2L).nome("Goku").build();
        var onePiece = builder().id(3L).nome("Luffy").build();
        listAnime.addAll(List.of(naruto, dragonBall, onePiece));
    }

    public static List<Anime> getList(){
        return listAnime;
    }

}
