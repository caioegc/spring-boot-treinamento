package caio.treinamento.inicio.repository;

import caio.treinamento.inicio.entity.Produtor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutorRepository extends JpaRepository<Produtor, Long> {

    List<Produtor> findByNome(String nome);

}
