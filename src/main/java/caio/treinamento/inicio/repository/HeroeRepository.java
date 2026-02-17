package caio.treinamento.inicio.repository;

import caio.treinamento.inicio.entity.Heroe;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HeroeRepository extends JpaRepository<Heroe, Long> {


    List<Heroe> findByNome(String nome);
}
