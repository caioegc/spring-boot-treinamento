package caio.treinamento.inicio.controller;

import caio.treinamento.inicio.entity.Produtor;
import caio.treinamento.inicio.mapper.ProducerMapper;
import caio.treinamento.inicio.mapper.ProducerMapperImpl;
import caio.treinamento.inicio.repository.DataRepository;
import caio.treinamento.inicio.repository.ProdutorRepository;
import caio.treinamento.inicio.service.ProdutorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@WebMvcTest(controllers = ProdutorController.class)
@Import({ProducerMapperImpl.class, ProdutorService.class, ProdutorRepository.class, DataRepository.class})
class ProdutorControllerTest {

   @Autowired
   private MockMvc mockMvc;

   @MockBean
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
   void testeReturnNull() throws Exception {
      BDDMockito.when(dataRepository.getProdutors()).thenReturn(produtorList);


      mockMvc.perform(MockMvcRequestBuilders.get("/v1/produtor"))
              .andDo(MockMvcResultHandlers.print())
              .andExpect(MockMvcResultMatchers.status().isOk())
              .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(1L));
   }

}