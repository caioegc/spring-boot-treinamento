package caio.treinamento.inicio.repository;

import caio.treinamento.inicio.entity.Heroe;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class HeroeRepositoryTest {

    @InjectMocks
    private HeroeRepository heroeRepository;

    @Mock
    private DataRepository dataRepository;

    private List<Heroe> heroe = new ArrayList<>();


    @BeforeEach
    void init() {
        var sasuke = Heroe.builder().id(1L).nome("Sasuke").atDate(LocalDateTime.now()).build();
        var gohan = Heroe.builder().id(2L).nome("Gohan").atDate(LocalDateTime.now()).build();
        var luffy = Heroe.builder().id(3L).nome("Luffy").atDate(LocalDateTime.now()).build();
        heroe.addAll(List.of(sasuke, gohan, luffy));

    }

    @Test
    void ListReturnAllIfIsSuccecs(){
        BDDMockito.when(dataRepository.getHEROES()).thenReturn(heroe);

        var heroes = heroeRepository.listAll();
        Assertions.assertThat(heroes).isNotNull().hasSize(heroes.size()).containsExactlyElementsOf(heroes);
    }

    @Test
    void listAllShouldReturnEmptyListIfRepositoryReturnsEmpty() {
        BDDMockito.when(dataRepository.getHEROES()).thenReturn(Collections.emptyList());

        var heroes = heroeRepository.listAll();

        Assertions.assertThat(heroes).isEmpty();
    }

    @Test
    void findById() {
        BDDMockito.when(dataRepository.getHEROES()).thenReturn(heroe);

        var heroe1 = heroe.get(0);

        var heroes = heroeRepository.listById(heroe1.getId());

        Assertions.assertThat(heroes).isPresent().contains(heroe1);
    }

    @Test
    void findByIdShouldReturnEmptyWhenNotFound() {
        BDDMockito.when(dataRepository.getHEROES()).thenReturn(heroe);

        var heroes = heroeRepository.listById(999L);

        Assertions.assertThat(heroes).isEmpty();
    }

    @Test
    void findByIdShouldReturnEmptyWhenRepositoryIsEmpty() {
        BDDMockito.when(dataRepository.getHEROES()).thenReturn(Collections.emptyList());

        var heroes = heroeRepository.listById(1L);

        Assertions.assertThat(heroes).isEmpty();
    }

    @Test
    void findByNameShouldReturnResultsWhenNameExists(){
        BDDMockito.when(dataRepository.getHEROES()).thenReturn(heroe);
        var nome = heroe.get(0).getNome();
        var heroes = heroeRepository.listByName(nome);

        Assertions.assertThat(heroes).isNotEmpty().allMatch(heroe -> heroe.getNome().equalsIgnoreCase(nome));
    }

    @Test
    void findByNameShouldReturnEmptyWhenNameIsNull() {
        BDDMockito.when(dataRepository.getHEROES()).thenReturn(heroe);

        var heroes = heroeRepository.listByName(null);

      Assertions.assertThat(heroes)
                .isNotNull().isEmpty();
    }

    @Test
    void createName(){
        BDDMockito.when(dataRepository.getHEROES()).thenReturn(heroe);
        var nomeBy = Heroe.builder().id(1L).nome("Caio").atDate(LocalDateTime.now()).build();

       var heroe1 = heroeRepository.createHeroe(nomeBy);

        Assertions.assertThat(heroe1).isEqualTo(nomeBy).hasNoNullFieldsOrProperties();

    }

    @Test
    void delete(){
        BDDMockito.when(dataRepository.getHEROES()).thenReturn(heroe);
        var delete = heroe.get(0);
        heroeRepository.deleteById(delete);

       var heroes = heroeRepository.listAll();

        Assertions.assertThat(heroes).isNotNull().doesNotContain(delete);
    }



}