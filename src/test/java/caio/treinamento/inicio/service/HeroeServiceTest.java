package caio.treinamento.inicio.service;

import caio.treinamento.inicio.entity.Heroe;
import caio.treinamento.inicio.entity.Produtor;
import caio.treinamento.inicio.repository.HeroeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HeroeServiceTest {

    @InjectMocks
    private HeroeService heroeService;

    @Mock
    private HeroeRepository heroeRepository;

    private List<Heroe> list;

    @BeforeEach
    void init() {
        {
            var komecco = Heroe.builder().id(1L).nome("komecco").build();
            var argo = Heroe.builder().id(2L).nome("argo").build();
            var rexona = Heroe.builder().id(3L).nome("Mandhouse").build();
            list = new ArrayList<>(List.of(komecco, argo, rexona));

        }
    }

    @Test
    void returnNullListAll(){

        BDDMockito.when(heroeRepository.findAll()).thenReturn(list);

        var heroes = heroeService.listAll(null);

        Assertions.assertThat(heroes).isNotNull().hasSameElementsAs(list);


}

    @Test
    void returnListByName(){
       var resourceByHeroe = list.get(0);
       var listByName = singletonList(resourceByHeroe);

       BDDMockito.when(heroeRepository.findByNome(resourceByHeroe.getNome())).thenReturn(listByName);

        var heroes = heroeService.listAll(resourceByHeroe.getNome());

        Assertions.assertThat(heroes).containsAll(listByName);

    }

    @Test
    void returnListByEmpty(){
        var nome = "vazio";
        BDDMockito.when(heroeRepository.findByNome(nome)).thenReturn(emptyList());

        var heroes = heroeService.listAll(nome);

        Assertions.assertThat(heroes).isNotNull().isEmpty();
    }

    @Test
    void returnById(){
        var heroe = list.get(0);
        BDDMockito.when(heroeRepository.findById(heroe.getId())).thenReturn(Optional.of(heroe));

        var heroe1 = heroeService.listById(heroe.getId());
        Assertions.assertThat(heroe1).isEqualTo(heroe);

    }


    @Test
    void returnByIdException(){
        var heroe = list.get(0);
        BDDMockito.when(heroeRepository.findById(heroe.getId())).thenReturn(Optional.empty());

        Assertions.assertThatException().isThrownBy(() -> heroeService.listById(heroe.getId()));
    }

    @Test
    void create(){
        var heroeCreate = Heroe.builder().id(5L).nome("Caio").atDate(LocalDateTime.now()).build();

        BDDMockito.when(heroeRepository.save(heroeCreate)).thenReturn(heroeCreate);

        var heroe = heroeService.create(heroeCreate);
        Assertions.assertThat(heroe).isEqualTo(heroeCreate).hasNoNullFieldsOrProperties();
    }

    @Test
    void deleteById(){
        var heroe = list.get(0);
        BDDMockito.when(heroeRepository.findById(heroe.getId())).thenReturn(Optional.of(heroe));

        Assertions.assertThatNoException().isThrownBy(() -> heroeService.delete(heroe.getId()));
    }

    @Test
    void deleteByIdCaseException(){
        var heroe = list.get(0);
        BDDMockito.when(heroeRepository.findById(heroe.getId())).thenReturn(Optional.empty());

        Assertions.assertThatException().isThrownBy(() -> heroeService.delete(heroe.getId()));
    }

    @Test
    void updateById(){
        var heroe = list.get(0);
        heroe.setNome("Caio");
        BDDMockito.when(heroeRepository.findById(heroe.getId())).thenReturn(Optional.of(heroe));

        Assertions.assertThatNoException().isThrownBy(()-> heroeService.update(heroe));
    }

    @Test
    void updateByIdCaseExpection(){
        var heroe = list.get(0);
        heroe.setNome("Caio");
        BDDMockito.when(heroeRepository.findById(heroe.getId())).thenReturn(Optional.empty());

        Assertions.assertThatException().isThrownBy(()-> heroeService.update(heroe)).isInstanceOf(ResponseStatusException.class);
    }




}


