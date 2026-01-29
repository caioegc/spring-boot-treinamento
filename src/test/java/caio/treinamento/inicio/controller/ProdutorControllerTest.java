package caio.treinamento.inicio.controller;

import caio.treinamento.inicio.entity.Produtor;
import caio.treinamento.inicio.mapper.ProducerMapper;
import caio.treinamento.inicio.mapper.ProducerMapperImpl;
import caio.treinamento.inicio.repository.DataRepository;
import caio.treinamento.inicio.repository.ProdutorRepository;
import caio.treinamento.inicio.service.ProdutorService;
import org.junit.jupiter.api.*;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ResourceLoader;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@WebMvcTest(controllers = ProdutorController.class)
//@Import({ProducerMapperImpl.class, ProdutorService.class, ProdutorRepository.class, DataRepository.class})
@ComponentScan(basePackages = "caio.treinamento")
class ProdutorControllerTest {

   @Autowired
   private MockMvc mockMvc;

   @MockBean
   private DataRepository dataRepository;
   private final List<Produtor> produtorList = new ArrayList<>();
    @Autowired
    private ResourceLoader resourceLoader;

   @BeforeEach
   void init() {
      {
         var dateTime = "2024-08-02T10:36:59.441554";
         var dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");
         var parse = LocalDateTime.parse(dateTime, dateTimeFormatter);
         var komecco = Produtor.builder().id(1L).nome("komecco").createdAt(parse).build();
         var argo = Produtor.builder().id(2L).nome("argo").createdAt(parse).build();
         var rexona = Produtor.builder().id(3L).nome("Mandhouse").createdAt(parse).build();
         var ufatable = Produtor.builder().id(4L).nome("Ufotable").createdAt(parse).build();

         produtorList.addAll(List.of(komecco, argo, rexona, ufatable));
      }
   }

   @Test
   void testeReturnNull() throws Exception {
      BDDMockito.when(dataRepository.getProdutors()).thenReturn(produtorList);
      var response = readResourceFile("produtor/get-produtor-null-name-200.json");

      mockMvc.perform(MockMvcRequestBuilders.get("/v1/produtor"))
              .andDo(MockMvcResultHandlers.print())
              .andExpect(MockMvcResultMatchers.status().isOk())
              .andExpect(MockMvcResultMatchers.content().json(response));
   }

   @Test
   @DisplayName("GET v1/producers?name=Ufotable returns list with found object when name exists")
   @Order(2)
   void findAll_ReturnsFoundProducerInList_WhenNameIsFound() throws Exception {
      BDDMockito.when(dataRepository.getProdutors()).thenReturn(produtorList);
      var response = readResourceFile("produtor/get-produtor-ufotable-name-200.json");
      var name = "Ufotable";

      mockMvc.perform(MockMvcRequestBuilders.get("/v1/producers").param("nome", name))
              .andDo(MockMvcResultHandlers.print())
              .andExpect(MockMvcResultMatchers.status().isOk())
              .andExpect(MockMvcResultMatchers.content().json(response));
   }





   private String readResourceFile(String fileName) throws IOException {
      var file = resourceLoader.getResource("classpath:%s".formatted(fileName)).getFile();
      return new String(Files.readAllBytes(file.toPath()));
   }
}