package caio.treinamento.inicio.service;

import caio.treinamento.inicio.entity.Profile;
import caio.treinamento.inicio.repository.ProfileRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class ProfileServiceTest {

    @Mock
    private ProfileRepository profileRepository;

    @InjectMocks
    private ProfileService profileService;

    List<Profile> list = new ArrayList<>();

    @BeforeEach
    void init(){
        var caio = Profile.builder().id(0L).nome("Caio").description("Lindaooo").build();
        var luiz = Profile.builder().id(1L).nome("Luiz").description("Feioo").build();
        var fernando = Profile.builder().id(2L).nome("Fernando").description("Mais ou menos").build();
        list.addAll(List.of(caio, luiz, fernando));

    }

    @Test
    void findAllIfParameterNull() {
        BDDMockito.when(profileRepository.findAll()).thenReturn(list);

        var all = profileService.findAll(null);

        Assertions.assertThat(all).hasSize(list.size());
    }

    @Test
    void findAllIfParameterExist() {
        var profile = list.get(0);
        BDDMockito.when(profileRepository.findByNome(profile.getNome())).thenReturn(list);

        var all = profileService.findAll(profile.getNome());

        Assertions.assertThat(all).isNotNull().contains(profile);
    }

    @Test
    void findAllIfParameterExistNoExist() {
        BDDMockito.when(profileRepository.findByNome("Jabuticaba")).thenReturn(Collections.emptyList());

        var all = profileService.findAll("Jabuticaba");

        Assertions.assertThat(all).isEmpty();
    }

    @Test
    void findById() {
        var profile = list.get(0);
        BDDMockito.when(profileRepository.findById(profile.getId())).thenReturn(Optional.of(profile));
        var byId = profileService.findById(profile.getId());
        Assertions.assertThat(byId).isEqualTo(profile).isNotNull();
    }

    @Test
    void findByIdIfNoExist() {
        var l = 5561651L;
        BDDMockito.when(profileRepository.findById(l)).thenReturn(Optional.empty());
        Assertions.assertThatException().isThrownBy(()-> profileService.findById(l)).isInstanceOf(NoSuchElementException.class);
    }


    @Test
    void saveIfOk() {
        var build = Profile.builder().id(4L).nome("Eliene").description("Lindaaa").build();
        BDDMockito.when(profileRepository.save(build)).thenReturn(build);
        var save = profileService.save(build);
        Assertions.assertThat(save).isNotNull().isEqualTo(build);
    }

    @Test
    void delete() {
        var profile = list.get(0);
        BDDMockito.when(profileRepository.findById(profile.getId())).thenReturn(Optional.of(profile));

        Assertions.assertThatNoException().isThrownBy(() -> profileService.delete(profile.getId()));
    }

    @Test
    void deleteIfHaveException() {

        BDDMockito.when(profileRepository.findById(5641561L)).thenReturn(Optional.empty());

        Assertions.assertThatException().isThrownBy(() -> profileService.delete(5641561L)).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void updateCaseSucesso() {

        var profile = list.get(0);
        profile.setNome("Paula");
        BDDMockito.when(profileRepository.findById(profile.getId())).thenReturn(Optional.of(profile));

        Assertions.assertThatNoException().isThrownBy(() -> profileService.update(profile));
    }

    @Test
    void updateCaseErro() {

        var profile = list.get(0);

        BDDMockito.when(profileRepository.findById(profile.getId())).thenReturn(Optional.empty());

        Assertions.assertThatException().isThrownBy(() -> profileService.update(profile)).isInstanceOf(NoSuchElementException.class);
    }
}