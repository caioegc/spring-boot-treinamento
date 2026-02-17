package caio.treinamento.inicio.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Produtor {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String nome;
    private LocalDateTime createdAt;


}
