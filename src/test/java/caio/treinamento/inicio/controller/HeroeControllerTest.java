package caio.treinamento.inicio.controller;

import caio.treinamento.inicio.entity.Heroe;
import caio.treinamento.inicio.mapper.HeroeMapperImpl;
import caio.treinamento.inicio.repository.DataRepository;
import caio.treinamento.inicio.repository.HeroeRepository;
import caio.treinamento.inicio.service.HeroeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.config.MvcNamespaceHandler;

import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@WebMvcTest(controllers = HeroeController.class)
@ComponentScan(basePackages = "caio.treinamento.inicio") // substitui o import abaixo pois é menor, o import se tivesse muita classe ia precisar exportar uma por uma
//@Import({HeroeMapperImpl.class, HeroeService.class, HeroeRepository.class, DataRepository.class})
class HeroeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DataRepository dataRepository;

    private List<Heroe> heroe = new ArrayList<>();

    @MockBean
    public external.dependency.Conections connections;

    @Autowired
    private ResourceLoader resourceLoader;

    @BeforeEach
    void init() {
        var dataTime = "2026-02-09T10:10:55.580602";
        var dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");
        var localDateTime = LocalDateTime.parse(dataTime, dateTimeFormatter);

        var sasuke = Heroe.builder().id(1L).nome("Sasuke").atDate(localDateTime).build();
        var gohan = Heroe.builder().id(2L).nome("Gohan").atDate(localDateTime).build();
        var luffy = Heroe.builder().id(3L).nome("Luffy").atDate(localDateTime).build();
        heroe.addAll(List.of(sasuke, gohan, luffy));

    }

    @Test
    @DisplayName("@DisplayName(GET v1/producers retorna uma lista com todos os produtores quando o argumento é nulo)")
    void buscarTodos_RetornaTodosHeroes_QuandoArgumentoForNulo() throws Exception {
        BDDMockito.when(dataRepository.getHEROES()).thenReturn(heroe);


        var response = readResource("heroe/get-heroe-null-name-200.json");
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/heroe"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(response));

    }

    @Test
    @DisplayName("@DisplayName(GET v1/producers retorna uma lista com todos os produtores quando o argumento é nulo)")
    void buscarTodos_RetornaTodosHeroes_QuandoArgumentoForNul() throws Exception {
        BDDMockito.when(dataRepository.getHEROES()).thenReturn(heroe);

        var response = readResource("heroe/get-heroe-sasuke-name-200.json");
        var nome = "Sasuke";
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/heroe").param("nome", nome))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(response));

    }

    @Test
    @DisplayName("@DisplayName(GET v1/producers retorna uma lista com todos os produtores quando o argumento não for encontrado)")
    void buscarTodos_RetornaTodosHeroes_QuandoArgumentoNaoForEncontrado() throws Exception {
        BDDMockito.when(dataRepository.getHEROES()).thenReturn(heroe);

        var response = readResource("heroe/get-heroe-x-name-200.json");
        var nome = "x";
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/heroe").param("nome", nome))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(response));

    }

    @Test
    @DisplayName("@DisplayName(GET v1/producers/{id} retorna uma lista com o heroe quando o argumentofor encontrado)")
    void buscarPorId_RetornarObjeto_QuandoArgumentoForEncontrado() throws Exception {
        BDDMockito.when(dataRepository.getHEROES()).thenReturn(heroe);

        var response = readResource("heroe/get-heroe-id-200.json");
        var id = 1L;
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/heroe/{id}", id))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(response));

    }

    @Test
    @DisplayName("@DisplayName(GET v1/producers/{id} retorna uma lista com o heroe quando o argumentofor encontrado)")
    void buscarPorId_RetornarErro_QuandoArgumentoNãoForEncontrado() throws Exception {
        BDDMockito.when(dataRepository.getHEROES()).thenReturn(heroe);
        var id = 999L;
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/heroe/{id}", id))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }



    private String readResource(String fileName) throws IOException {
        var file = resourceLoader.getResource("classpath:%s".formatted(fileName)).getFile();
        return new String(Files.readAllBytes(file.toPath()));
    }

}