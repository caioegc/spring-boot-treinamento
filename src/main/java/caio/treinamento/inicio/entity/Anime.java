package caio.treinamento.inicio.entity;

import java.util.ArrayList;
import java.util.List;

public class Anime {
    List<String> list;

    public Anime() {
        this.list = new ArrayList<>();
    }

    public List<String> animeList(){
        list.add("Naruto");
        list.add("Dragon Ball z");
        list.add("One piece");
        list.add("HunterxHunter");
        return list;
    }
}
