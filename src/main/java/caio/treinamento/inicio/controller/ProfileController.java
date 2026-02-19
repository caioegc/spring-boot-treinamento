package caio.treinamento.inicio.controller;


import caio.treinamento.inicio.entity.Profile;
import caio.treinamento.inicio.mapper.ProfileMapper;
import caio.treinamento.inicio.request.ProfilePostRequest;
import caio.treinamento.inicio.request.ProfilePutRequest;
import caio.treinamento.inicio.response.ProfileGetResponse;
import caio.treinamento.inicio.response.ProfilePostResponse;
import caio.treinamento.inicio.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/profile")
@RequiredArgsConstructor
public class ProfileController {
   private final ProfileService profileService;
   private final ProfileMapper mapper;

    @GetMapping
    public ResponseEntity<List<ProfileGetResponse>> findAll(@RequestParam(required = false) String nome){
        var all = profileService.findAll(nome);
        var list = mapper.list(all);
        return ResponseEntity.ok(list);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProfileGetResponse> findALL(@PathVariable Long id){
        var byId = profileService.findById(id);
        var profileGetResponse = mapper.listGet(byId);

        return ResponseEntity.ok(profileGetResponse);
    }

    @PostMapping
    public ResponseEntity<ProfilePostResponse> save(@RequestBody ProfilePostRequest profilePostRequest1){
        var profilePostRequest = mapper.createdRequest(profilePostRequest1);
        var save = profileService.save(profilePostRequest);
        var profilePostResponse = mapper.createdResponse(save);
        return ResponseEntity.status(HttpStatus.CREATED).body(profilePostResponse);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody ProfilePutRequest profilePutRequest){
        var profile = mapper.updateRequest(profilePutRequest);
        profileService.update(profile);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        profileService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
