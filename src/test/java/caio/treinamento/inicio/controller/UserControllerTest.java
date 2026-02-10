package caio.treinamento.inicio.controller;

import caio.treinamento.inicio.entity.User;
import caio.treinamento.inicio.repository.DataUserRepository;
import caio.treinamento.inicio.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
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
import java.util.ArrayList;
import java.util.List;

@WebMvcTest(controllers = UserController.class)
//@Import({UserMapperImpl.class, UserService.class, UserRepository.class, DataUserRepository.class})
@ComponentScan(basePackages = "caio.treinamento.inicio")
class UserControllerTest {
    @MockBean
    public external.dependency.Conections connections;

    @Autowired
    private MockMvc mockMvc;


    public List<User> userList = new ArrayList<>();
    @Autowired
    private ResourceLoader resourceLoader;

    @MockBean
    private DataUserRepository data;

    @SpyBean
    private UserRepository repository;

    @BeforeEach
    void init() {
        {
            var user1 = User.builder().id(1L).email("dudueocara77@gmail.com").primeiroNome("Caio").ultimoNome("Carvalho").build();
            var user2 = User.builder().id(2L).email("dudueocara77@gmail.com").primeiroNome("Andre").ultimoNome("Carvalho").build();
            var user3 = User.builder().id(3L).email("dudueocara77@gmail.com").primeiroNome("Eliene").ultimoNome("Carvalho").build();
            var user4 = User.builder().id(4L).email("dudueocara77@gmail.com").primeiroNome("Rafael").ultimoNome("Carvalho").build();
            userList.addAll(List.of(user1, user2, user3, user4));
        }
    }

    @Test
    void retorneTodosQuandoNaoTiverParametros() throws Exception {

        BDDMockito.when(data.getUser()).thenReturn(userList);

        var resource = fileLoader("user/user-get-response-200.json");

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/user"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(resource));
    }

    @Test
    void retorneQuandoTiverComoParametroNome() throws Exception {
        BDDMockito.when(data.getUser()).thenReturn(userList);
        var response = fileLoader("user/user-get-name-caio-response-200.json");
        var nome = "Caio";

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/user").param("nome", nome))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(response));

    }

    @Test
    void retorneListaAPartirDoId() throws Exception {
        BDDMockito.when(data.getUser()).thenReturn(userList);
        var id = 1L;

        var resource = fileLoader("user/user-get-id-response-200.json");

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/user/{id}", id))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(resource));
    }

    @Test
    void retorneExceccaoQuandoIdInvalido() throws Exception {
        BDDMockito.when(data.getUser()).thenReturn(userList);

        var id = 991256156l;

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/user/{id}", id))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    @Test
    void salveQuandoForValido() throws Exception {

        var build = User.builder().id(99L).primeiroNome("Samuel").ultimoNome("Xavier").email("samuca@gmail.com").build();
        BDDMockito.when(repository.createUser(ArgumentMatchers.any())).thenReturn(build);

        var request = fileLoader("user/user-post-request-200.json");
        var response = fileLoader("user/user-post-response-201.json");

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(response));

    }

    @Test
    void AtualizeQuandoForValido() throws Exception {

        BDDMockito.when(data.getUser()).thenReturn(userList);
        var request = fileLoader("user/user-put-request-200.json");

        mockMvc.perform(MockMvcRequestBuilders.put("/v1/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNoContent());

    }

    @Test
    void lancarUmaExcecaoQuandoTiverErro() throws Exception {

        BDDMockito.when(data.getUser()).thenReturn(userList);
        var request = fileLoader("user/user-put-request-erro-400.json");

        mockMvc.perform(MockMvcRequestBuilders.put("/v1/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }


    @Test
    void deletarQuandoForUmSucesso() throws Exception {
        BDDMockito.when(data.getUser()).thenReturn(userList);
        var id = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/user/{id}", id))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void lancarUmaExceptionQuandoTiverAlgumErro() throws Exception {
        BDDMockito.when(data.getUser()).thenReturn(userList);
        var id = 958L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/user/{id}", id))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    private String fileLoader(String nomeFile) throws IOException {
        var file = resourceLoader.getResource("classpath:%s".formatted(nomeFile)).getFile();
        return new String(Files.readAllBytes(file.toPath()));
    }

}
