package caio.treinamento.inicio.repository;

import caio.treinamento.inicio.entity.User;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {

    List<User> findByPrimeiroNome(String primeiroNome);

    Optional<User> findByEmail(String email);
}
