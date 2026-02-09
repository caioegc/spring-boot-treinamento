package caio.treinamento.inicio.controller;

import caio.treinamento.inicio.entity.Heroe;
import caio.treinamento.inicio.repository.DataRepository;
import caio.treinamento.inicio.repository.HeroeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
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

    @SpyBean
    private HeroeRepository heroeRepository;

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

    @Test
    @DisplayName("@DisplayName(POST v1/Heroe salva quando for um sucesso")
    void salvamentoFeitoQuandoForUmSucesso() throws Exception {
        var request = readResource("heroe/post-request-heroe-200.json");
        var response = readResource("heroe/post-response-heroe-201.json");

        var novoHeroeCriado = Heroe.builder().id(99L).nome("Nagato").atDate(LocalDateTime.now()).build();
        BDDMockito.when(heroeRepository.createHeroe(ArgumentMatchers.any())).thenReturn(novoHeroeCriado);


        mockMvc.perform(MockMvcRequestBuilders
                        .post("/v1/heroe")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(response));


    }

    @Test
    void atualizeQuandoforUmSucesso() throws Exception {
        BDDMockito.when(dataRepository.getHEROES()).thenReturn(heroe);
        var request = readResource("heroe/put-request-heroe-200.json");

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/v1/heroe")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNoContent());

    }

    @Test
    void respostaSeNaoExistir() throws Exception {
        BDDMockito.when(dataRepository.getHEROES()).thenReturn(heroe);
        var request = readResource("heroe/put-request-heroe-404.json");

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/v1/heroe")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    @Test
    void deleteQuandoForUmSucesso() throws Exception {
        BDDMockito.when(dataRepository.getHEROES()).thenReturn(heroe);
        var id = heroe.get(0).getId();
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/v1/heroe/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNoContent());

    }

    @Test
    void deleteQuandoForUmErro() throws Exception {
        BDDMockito.when(dataRepository.getHEROES()).thenReturn(heroe);
        var id = 151561561L;
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/v1/heroe/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    private String readResource(String fileName) throws IOException {
        var file = resourceLoader.getResource("classpath:%s".formatted(fileName)).getFile();
        return new String(Files.readAllBytes(file.toPath()));
    }

}