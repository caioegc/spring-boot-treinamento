package caio.treinamento.inicio.controller;

import caio.treinamento.inicio.entity.Profile;
import caio.treinamento.inicio.entity.User;
import caio.treinamento.inicio.mapper.ProfileMapperImpl;
import caio.treinamento.inicio.repository.ProfileRepository;
import caio.treinamento.inicio.service.ProfileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(controllers = ProfileController.class)
@Import({ProfileService.class, ProfileRepository.class, ProfileMapperImpl.class})
class ProfileControllerTest {

    @Autowired
   public MockMvc mockMvc;

    @MockBean
    private ProfileRepository profileRepository;
    @Autowired
    private ResourceLoader resourceLoader;

    List<Profile> list = new ArrayList<>();

    @BeforeEach
    void init(){
        var caio = Profile.builder().id(0L).nome("Caio").description("Lindaooo").build();
        var luiz = Profile.builder().id(1L).nome("Luiz").description("Feioo").build();
        var fernando = Profile.builder().id(2L).nome("Fernando").description("Mais ou menos").build();
        list.addAll(List.of(caio, luiz, fernando));

    }


    @Test
    void findAll() throws Exception {
        var response = fileLoader("profile/profile-get-200.json");
        BDDMockito.when(profileRepository.findAll()).thenReturn(list);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/profile"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(response));
    }

    @Test
    void findAllCaseName() throws Exception {
        var response = fileLoader("profile/profile-get-caio-200.json");
        var nome = "Caio";
        var first = list.stream().filter(a -> a.getNome().equalsIgnoreCase(nome)).findFirst().orElseThrow();
        BDDMockito.when(profileRepository.findByNome(nome)).thenReturn(Collections.singletonList(first));

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/profile").param("nome", nome))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(response));
    }


    @Test
    void findById() throws Exception {
        var id = 0L;
        var first = list.stream().filter(a -> a.getId().equals(id)).findFirst().orElseThrow();
        BDDMockito.when(profileRepository.findById(id)).thenReturn(Optional.of(first));

        var response = fileLoader("profile/profile-get-id-200.json");


        mockMvc.perform(MockMvcRequestBuilders.get("/v1/profile/{id}", id))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(response));
    }

    @Test
    void findByIdIfNoExist() throws Exception {
        var id = 55L;

        BDDMockito.when(profileRepository.findById(id))
                .thenThrow(new NoSuchElementException());

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/profile/{id}", id))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void save() {
        P
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    public String fileLoader(String nomeFile) throws IOException {
        var file = resourceLoader.getResource("classpath:%s".formatted(nomeFile)).getFile();
        return new String(Files.readAllBytes(file.toPath()));
    }
}