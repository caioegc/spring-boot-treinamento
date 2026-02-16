package caio.treinamento.inicio.controller;

import caio.treinamento.inicio.commons.FileUtils;
import caio.treinamento.inicio.commons.UserUtils;
import caio.treinamento.inicio.entity.User;
import caio.treinamento.inicio.repository.DataUserRepository;
import caio.treinamento.inicio.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

@WebMvcTest(controllers = UserController.class)
//@Import({UserMapperImpl.class, UserService.class, UserRepository.class, DataUserRepository.class})
@ComponentScan(basePackages = "caio.treinamento.inicio")
//@ActiveProfiles("test")
class UserControllerTest {
    @MockBean
    public external.dependency.Conections connections;

    @Autowired
    private MockMvc mockMvc;

    private static final String URL = "/v1/user";

    public List<User> userList = new ArrayList<>();

    @Autowired
    private FileUtils fileUtils;

    @MockBean
    private DataUserRepository data;

    @SpyBean
    private UserRepository repository;

    @Autowired
    private UserUtils userUtils;

    @BeforeEach
    void init() {
        {
            userList = userUtils.getList();
        }
    }

    @Test
    void retorneTodosQuandoNaoTiverParametros() throws Exception {

        BDDMockito.when(data.getUser()).thenReturn(userList);

        var resource = fileUtils.fileLoader("user/user-get-response-200.json");

        mockMvc.perform(MockMvcRequestBuilders.get(URL))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(resource));
    }

    @Test
    void retorneQuandoTiverComoParametroNome() throws Exception {
        BDDMockito.when(data.getUser()).thenReturn(userList);
        var response = fileUtils.fileLoader("user/user-get-name-caio-response-200.json");
        var nome = "Caio";

        mockMvc.perform(MockMvcRequestBuilders.get(URL).param("nome", nome))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(response));

    }

    @Test
    void retorneListaAPartirDoId() throws Exception {
        BDDMockito.when(data.getUser()).thenReturn(userList);
        var id = 1L;

        var resource = fileUtils.fileLoader("user/user-get-id-response-200.json");

        mockMvc.perform(MockMvcRequestBuilders.get(URL + "/{id}", id))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(resource));
    }

    @Test
    void retorneExceccaoQuandoIdInvalido() throws Exception {
        BDDMockito.when(data.getUser()).thenReturn(userList);

        var id = 991256156l;

        mockMvc.perform(MockMvcRequestBuilders.get(URL + "/{id}", id))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    @Test
    void salveQuandoForValido() throws Exception {

        var build = User.builder().id(99L).primeiroNome("Samuel").ultimoNome("Xavier").email("samuca@gmail.com").build();
        BDDMockito.when(repository.createUser(ArgumentMatchers.any())).thenReturn(build);

        var request = fileUtils.fileLoader("user/user-post-request-200.json");
        var response = fileUtils.fileLoader("user/user-post-response-201.json");

        mockMvc.perform(MockMvcRequestBuilders.post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(response));

    }

    @Test
    void AtualizeQuandoForValido() throws Exception {

        BDDMockito.when(data.getUser()).thenReturn(userList);
        var request = fileUtils.fileLoader("user/user-put-request-200.json");

        mockMvc.perform(MockMvcRequestBuilders.put(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNoContent());

    }

    @Test
    void lancarUmaExcecaoQuandoTiverErro() throws Exception {

        BDDMockito.when(data.getUser()).thenReturn(userList);
        var request = fileUtils.fileLoader("user/user-put-request-erro-400.json");

        mockMvc.perform(MockMvcRequestBuilders.put(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }


    @Test
    void deletarQuandoForUmSucesso() throws Exception {
        BDDMockito.when(data.getUser()).thenReturn(userList);
        var id = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete(URL + "/{id}", id))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void lancarUmaExceptionQuandoTiverAlgumErro() throws Exception {
        BDDMockito.when(data.getUser()).thenReturn(userList);
        var id = 958L;

        mockMvc.perform(MockMvcRequestBuilders.delete(URL + "/{id}", id))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }


    @ParameterizedTest
    @MethodSource("postUseBadRequest")
    void salvar_RetornaBadRequest_QuandoCamposEstaoVazios(String fileName, List<String> errors) throws Exception {

        var request = fileUtils.fileLoader("user/%s".formatted(fileName));

        var build = User.builder().id(99L).primeiroNome("Samuel").ultimoNome("Xavier").email("samuca@gmail.com").build();
        BDDMockito.when(repository.createUser(ArgumentMatchers.any())).thenReturn(build);


        var mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();

        var resolvedException = mvcResult.getResolvedException();

        Assertions.assertThat(resolvedException).isNotNull();


        Assertions
                .assertThat(resolvedException.getMessage())
                .contains(errors);

    }

    @ParameterizedTest
    @MethodSource("argumentsInvalid")
    void AtualizeQuandoNaoForInvalido(String fileName, List<String> errors) throws Exception {

        BDDMockito.when(data.getUser()).thenReturn(userList);
        var request = fileUtils.fileLoader("user/%s".formatted(fileName));

        var mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();

        var resolvedException = mvcResult.getResolvedException();
        Assertions.assertThat(resolvedException).isNotNull();

        Assertions.assertThat(resolvedException.getMessage())
                .contains(errors);

    }

    private static Stream<Arguments> argumentsInvalid(){
        var idVazio = "id não pode ficar nulo";
        var nomeVazio = "primeiro não pode ficar vazio";
        var ultimoNomeVazio = "ultimoNome não pode ficar vazio";
        var emailVazio = "E-mail não pode ficar vazio";

        var emailInvalido = "E-mail não pode ser invalido";

        var errosList = List.of(idVazio, nomeVazio, ultimoNomeVazio, emailVazio);
        var emailError = Collections.singletonList(emailInvalido);

        return Stream.of(
                Arguments.of("user-put-request-empty-400.json", errosList),
                Arguments.of("user-put-request-blank-400.json", errosList),
                Arguments.of("user-put-request-email-invalido-400.json", emailError)
                );

    }


    private static Stream<Arguments> postUseBadRequest(){

        var primeiroNomeInvalido = "O primeiro nome não pode ser nulo e precisa ter pelo menos 1 caracter";
        var ultimoNomeInvalido = "O segundo nome não pode ser nulo e precisa ter pelo menos 1 caracter";
        var emailInvalido = "O email não pode ser nulo e precisa ter pelo menos 1 caracter";
        var emailParametroInvalido = "O e-mail não é valido";

        var listError = List.of(primeiroNomeInvalido, ultimoNomeInvalido, emailInvalido);
        var emailError = Collections.singletonList(emailParametroInvalido);

        return Stream.of(
                Arguments.of("user-post-request-empty-400.json",listError ),
                Arguments.of("user-post-request-blank-400.json",listError),
                Arguments.of("user-post-request-email-invalido400.json", emailError)
        );
    }


    

}
