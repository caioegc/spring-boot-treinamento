package caio.treinamento.inicio.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


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
