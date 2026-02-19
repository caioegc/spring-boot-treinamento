package caio.treinamento.inicio.entity;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;

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

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "heroe_seq")
    @SequenceGenerator(
            name = "heroe_seq",
            sequenceName = "HERoe_SEQ",
            allocationSize = 1
    )
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false, insertable = false, updatable = false )
    @CreationTimestamp(source = SourceType.DB)
    private LocalDateTime atDate;

}
