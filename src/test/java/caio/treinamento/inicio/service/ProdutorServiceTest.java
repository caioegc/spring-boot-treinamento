//package caio.treinamento.inicio.service;
//
//import caio.treinamento.inicio.entity.Produtor;
//import caio.treinamento.inicio.repository.ProdutorRepository;
//
//import org.junit.jupiter.api.*;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.BDDMockito;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.web.server.ResponseStatusException;
//import org.assertj.core.api.Assertions;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static java.util.Collections.emptyList;
//import static java.util.Collections.singletonList;
//
//@ExtendWith(MockitoExtension.class)
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//class ProdutorServiceTest {
//
//
//    @InjectMocks
//    private ProdutorService produtorService;
//
//    @Mock
//    private ProdutorRepository produtorRepository;
//
//    private List<Produtor> list;
//
//
//    @BeforeEach
//    void init() {
//        {
//            var komecco = Produtor.builder().id(1L).nome("komecco").createdAt(LocalDateTime.now()).build();
//            var argo = Produtor.builder().id(2L).nome("argo").createdAt(LocalDateTime.now()).build();
//            var rexona = Produtor.builder().id(3L).nome("Mandhouse").createdAt(LocalDateTime.now()).build();
//            list = new ArrayList<>(List.of(komecco, argo, rexona));
//        }
//    }
//
//    @Test
//    @Order(1)
//    void testeReturnNull(){
//        BDDMockito.when(produtorRepository.produtorList()).thenReturn(list);
//        var produtors = produtorService.produtorList(null);
//
//
//        Assertions.assertThat(produtors).isNotNull().hasSameElementsAs(list);
//    }
//
//    @Test
//    @Order(1)
//    void returnListByNome(){
//       var producer = list.get(0);
//       var produtors1 = singletonList(producer);
//        BDDMockito.when(produtorRepository.listByNome(producer.getNome())).thenReturn(produtors1);
//        var produtors = produtorService.produtorList(producer.getNome());
//
//        Assertions.assertThat(produtors).containsAll(produtors1);
//    }
//
//    @Test
//    @Order(3)
//    void returnListByEmpty(){
//
//        var nome = "notFound";
//
//        BDDMockito.when(produtorRepository.listByNome(nome)).thenReturn(emptyList());
//
//        var empty = produtorService.produtorList(nome);
//
//        Assertions.assertThat(empty).isNotNull().isEmpty();
//
//    }
//
//    @Test
//    @Order(4)
//    void returnById(){
//        var produtor = list.get(0);
//        BDDMockito.when(produtorRepository.byId(produtor.getId())).thenReturn(Optional.of(produtor));
//
//        var produtor1 = produtorService.listById(produtor.getId());
//
//        Assertions.assertThat(produtor1).isEqualTo(produtor);
//    }
//
//    @Test
//    @Order(5)
//    void returnByIdNotFound(){
//        var produtor = list.get(0);
//        BDDMockito.when(produtorRepository.byId(produtor.getId())).thenReturn(Optional.empty());
//
//      Assertions.assertThatException().isThrownBy(() -> produtorService.listById(produtor.getId())).isInstanceOf(ResponseStatusException.class);
//
//    }
//
//    @Test
//    @Order(6)
//    void saveProductor(){
//        var expectedProdutor = Produtor.builder().id(5L).nome("Caio").createdAt(LocalDateTime.now()).build();
//        BDDMockito.when(produtorRepository.create(expectedProdutor)).thenReturn(expectedProdutor);
//
//        var produtor = produtorService.create(expectedProdutor);
//
//        Assertions.assertThat(produtor).isEqualTo(expectedProdutor).hasNoNullFieldsOrProperties();
//
//    }
//
//    @Test
//    @Order(7)
//    void deleteById(){
//        var produtor = list.get(0);
//        BDDMockito.when(produtorRepository.byId(produtor.getId())).thenReturn(Optional.of(produtor));
//
//        BDDMockito.doNothing().when(produtorRepository).delete(produtor);
//
//        Assertions.assertThatNoException().isThrownBy(() -> produtorService.delete(produtor.getId()));
//    }
//
//
//    @Test
//    @Order(8)
//    void deleteByIdCaseThrowException(){
//        var produtor = list.get(0);
//        BDDMockito.when(produtorRepository.byId(produtor.getId())).thenReturn(Optional.empty());
//        Assertions.assertThatException().isThrownBy(() -> produtorService.delete(produtor.getId())).isInstanceOf(ResponseStatusException.class);
//    }
//
//
//    @Test
//    @Order(9)
//    void updateCaseNoTrhowException(){
//        var produtor = list.get(0);
//        produtor.setNome("Caio");
//        BDDMockito.when(produtorRepository.byId(produtor.getId())).thenReturn(Optional.of(produtor));
//        BDDMockito.doNothing().when(produtorRepository).update(produtor);
//        Assertions.assertThatNoException().isThrownBy(() -> produtorService.update(produtor));
//
//    }
//
//    @Test
//    @Order(10)
//    void updateCaseTrhowException(){
//        var produtor = list.get(0);
//        produtor.setNome("Caio");
//        BDDMockito.when(produtorRepository.byId(produtor.getId())).thenReturn(Optional.empty());
//        Assertions.assertThatException().isThrownBy(() -> produtorService.update(produtor)).isInstanceOf(ResponseStatusException.class);
//
//    }
//
//}