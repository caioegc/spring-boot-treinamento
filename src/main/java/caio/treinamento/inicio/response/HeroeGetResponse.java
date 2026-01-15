package caio.treinamento.inicio.response;

import caio.treinamento.inicio.entity.Heroe;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class HeroeGetResponse {

    @EqualsAndHashCode.Include
    private long id;

    private String nome;
    private LocalDateTime atDate;
    public static List<Heroe> list = new ArrayList<>();

}
