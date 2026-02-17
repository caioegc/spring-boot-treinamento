package caio.treinamento.inicio.controller;

import caio.treinamento.inicio.commons.FileUtils;
import caio.treinamento.inicio.entity.Heroe;
import caio.treinamento.inicio.external.dependency.Conections;
import caio.treinamento.inicio.mapper.HeroeMapperImpl;
import caio.treinamento.inicio.repository.HeroeRepository;
import caio.treinamento.inicio.service.HeroeService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;


@WebMvcTest(controllers = HeroeController.class)
//@ComponentScan(basePackages = "caio.treinamento.inicio") // substitui o import abaixo pois é menor, o import se tivesse muita classe ia precisar exportar uma por uma
@Import({HeroeMapperImpl.class, HeroeService.class, HeroeRepository.class, FileUtils.class})
class HeroeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FileUtils fileUtils;

    private List<Heroe> heroe = new ArrayList<>();

    @MockBean
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
        BDDMockito.when(heroeRepository.findAll()).thenReturn(heroe);


        var response = fileUtils.fileLoader("heroe/get-heroe-null-name-200.json");
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/heroe"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(response));

    }

    @Test
    @DisplayName("@DisplayName(GET v1/producers retorna uma lista com todos os produtores quando o argumento é nulo)")
    void buscarTodos_RetornaTodosHeroes_QuandoArgumentoForNul() throws Exception {
        var nome = "Sasuke";
        var sasuke = heroe.stream().filter(heroe -> heroe.getNome().equals(nome)).findFirst().orElseThrow();
        BDDMockito.when(heroeRepository.findByNome(nome)).thenReturn(Collections.singletonList(sasuke));

        var response = fileUtils.fileLoader("heroe/get-heroe-sasuke-name-200.json");
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/heroe").param("nome", nome))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(response));

    }

    @Test
    @DisplayName("@DisplayName(GET v1/producers retorna uma lista com todos os produtores quando o argumento não for encontrado)")
    void buscarTodos_RetornaTodosHeroes_QuandoArgumentoNaoForEncontrado() throws Exception {

        var response = fileUtils.fileLoader("heroe/get-heroe-x-name-200.json");
        var nome = "x";
        var heroeList = heroe.stream().filter(heroe -> heroe.getNome().equals(nome)).findFirst();
        BDDMockito.when(heroeRepository.findByNome(nome)).thenReturn(Collections.emptyList());
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/heroe").param("nome", nome))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(response));

    }

    @Test
    @DisplayName("@DisplayName(GET v1/producers/{id} retorna uma lista com o heroe quando o argumentofor encontrado)")
    void buscarPorId_RetornarObjeto_QuandoArgumentoForEncontrado() throws Exception {
        var id = 1L;
        var heroe1 = heroe.stream().filter(heroe -> heroe.getId().equals(id)).findFirst();
        BDDMockito.when(heroeRepository.findById(id)).thenReturn(heroe1);

        var response = fileUtils.fileLoader("heroe/get-heroe-id-200.json");
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/heroe/{id}", id))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(response));

    }

    @Test
    @DisplayName("@DisplayName(GET v1/producers/{id} retorna uma lista com o heroe quando o argumentofor encontrado)")
    void buscarPorId_RetornarErro_QuandoArgumentoNãoForEncontrado() throws Exception {
        var id = 999L;
        var heroe1 = heroe.stream().filter(heroe -> heroe.getId().equals(id)).findFirst();
        BDDMockito.when(heroeRepository.findById(id)).thenReturn(heroe1);
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/heroe/{id}", id))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    @Test
    @DisplayName("@DisplayName(POST v1/Heroe salva quando for um sucesso")
    void salvamentoFeitoQuandoForUmSucesso() throws Exception {
        var request = fileUtils.fileLoader("heroe/post-request-heroe-200.json");
        var response = fileUtils.fileLoader("heroe/post-response-heroe-201.json");

        var novoHeroeCriado = Heroe.builder().id(99L).nome("Nagato").atDate(LocalDateTime.now()).build();
        BDDMockito.when(heroeRepository.save(ArgumentMatchers.any())).thenReturn(novoHeroeCriado);


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

        var id = 1L;

        var user1 = heroe.stream().filter(heroe -> heroe.getId().equals(id)).findFirst();
        BDDMockito.when(heroeRepository.findById(id)).thenReturn(user1);
        var request = fileUtils.fileLoader("heroe/put-request-heroe-200.json");

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/v1/heroe")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNoContent());

    }

    @Test
    void respostaSeNaoExistir() throws Exception {
        var request = fileUtils.fileLoader("heroe/put-request-heroe-404.json");

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/v1/heroe")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    @Test
    void deleteQuandoForUmSucesso() throws Exception {
        var id = 1L;
        var user1 = heroe.stream().filter(heroe -> heroe.getId().equals(id)).findFirst();
        BDDMockito.when(heroeRepository.findById(id)).thenReturn(user1);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/v1/heroe/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNoContent());

    }

    @Test
    void deleteQuandoForUmErro() throws Exception {
        var id = 151561561L;

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/v1/heroe/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    @ParameterizedTest
    @MethodSource("postErrors")
    @DisplayName("@DisplayName(POST v1/Heroe salva quando for um sucesso")
    void salvamentoNaoFeitoPorEstarInvalido(String fileName, String errors) throws Exception {
        var request = fileUtils.fileLoader("heroe/%s".formatted(fileName));

        var novoHeroeCriado = Heroe.builder().id(99L).nome("Nagato").atDate(LocalDateTime.now()).build();
        BDDMockito.when(heroeRepository.save(ArgumentMatchers.any())).thenReturn(novoHeroeCriado);


        var mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .post("/v1/heroe")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();

        var resolvedException = mvcResult.getResolvedException();

        Assertions.assertThat(resolvedException).isNotNull();

        Assertions.assertThat(resolvedException.getMessage()).contains(errors);

    }

    @ParameterizedTest
    @MethodSource("putError")
    void derErroQuandoOAtualizarEstiverInvalido(String fileName, List<String> erros) throws Exception {

        var request = fileUtils.fileLoader("heroe/%s".formatted(fileName));

        var mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .put("/v1/heroe")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();

        var resolvedException = mvcResult.getResolvedException();

        Assertions.assertThat(resolvedException).isNotNull();
        Assertions.assertThat(resolvedException.getMessage()).contains(erros);

    }

    private static Stream<Arguments> putError(){
        var idNulo = "Id não pode ser nulo";
        var nomeEmBranco = "nome não pode ficar em branco";

        var erro = List.of(idNulo, nomeEmBranco);

        return Stream.of(Arguments.of("put-request-heroe-blank-400.json", erro),
                Arguments.of("put-request-heroe-empty-400.json", erro));
    }

    private static Stream<Arguments> postErrors(){
        var nomeInvalido = "Não pode ficar nulo";

        return Stream.of(
                Arguments.of("post-request-heroe-blank-400.json",nomeInvalido),
                Arguments.of("post-request-heroe-empty-400.json", nomeInvalido)
        );
    }


    

}