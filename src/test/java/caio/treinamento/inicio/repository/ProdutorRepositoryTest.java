package caio.treinamento.inicio.repository;

import caio.treinamento.inicio.entity.Produtor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class ProdutorRepositoryTest {

    @InjectMocks
    private ProdutorRepository produtorRepository;

    @Mock
    private DataRepository dataRepository;
    private final List<Produtor> produtorList = new ArrayList<>();

    @BeforeEach
    void init() {
        {
            var komecco = Produtor.builder().id(1L).nome("komecco").createdAt(LocalDateTime.now()).build();
            var argo = Produtor.builder().id(2L).nome("argo").createdAt(LocalDateTime.now()).build();
            var rexona = Produtor.builder().id(3L).nome("Mandhouse").createdAt(LocalDateTime.now()).build();
            produtorList.addAll(List.of(komecco, argo, rexona));
        }
    }

    @Test
    @Order(1)
    @DisplayName("Return list with names the productor")
    void findAll_ReturnAllProducers_WhenSuccecs(){
        BDDMockito.when(dataRepository.getProdutors()).thenReturn(produtorList);

        var produtors = produtorRepository.produtorList();
        Assertions.assertThat(produtors).isNotNull().hasSize(produtors.size());

    }

    @Test
    @Order(2)
    void findById(){
        BDDMockito.when(dataRepository.getProdutors()).thenReturn(produtorList);
        var expectedProductor = produtorList.get(0);
        var produtors = produtorRepository.byId(expectedProductor.getId());

        Assertions.assertThat(produtors).isPresent().contains(expectedProductor);
    }


    @Test
    @Order(3)
    void findByName(){
        BDDMockito.when(dataRepository.getProdutors()).thenReturn(produtorList);
        var produtors = produtorRepository.listByNome(null);

        Assertions.assertThat(produtors).isNotNull().isEmpty();
    }


    @Test
    @Order(4)
    void findByNameDifferentNull(){
        BDDMockito.when(dataRepository.getProdutors()).thenReturn(produtorList);
        var expectedProductor = produtorList.get(0);

        var produtors = produtorRepository.listByNome(expectedProductor.getNome());

        Assertions.assertThat(produtors).contains(expectedProductor);
    }


}