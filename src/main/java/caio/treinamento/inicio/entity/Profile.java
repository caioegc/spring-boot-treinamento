package caio.treinamento.inicio.entity;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
   private Long id;
    @Column(nullable = false)
   private String nome;
    @Column(nullable = false)
    private String description;
}
