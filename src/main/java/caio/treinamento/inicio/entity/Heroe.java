package caio.treinamento.inicio.entity;

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
public class Heroe {

    @EqualsAndHashCode.Include
    private Long id;

    private String nome;
    private LocalDateTime atDate;
    public static List<Heroe> list = new ArrayList<>();

}
