package caio.treinamento.inicio.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Heroe {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String nome;
    private LocalDateTime atDate;

}
