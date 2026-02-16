package caio.treinamento.inicio.controller;

import caio.treinamento.inicio.mapper.UserMapper;
import caio.treinamento.inicio.response.UserResponse;
import caio.treinamento.inicio.service.UserService;
import caio.treinamento.request.UserRequest;
import caio.treinamento.request.UserRequestPut;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<UserResponse>> listUser(@RequestParam(required = false) String nome){
        var users = userService.listUser(nome);
        var user = userMapper.toUserGet(users);
        return ResponseEntity.ok(user);

}

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> listById(@PathVariable Long id){
        var user = userService.listById(id);
        var userGet = userMapper.toUserGet(user);
        return ResponseEntity.ok(userGet);

    }


    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest userRequest){


        var user1 = userMapper.toUser(userRequest);
        var user = userService.create(user1);
        var userGet = userMapper.toUserGet(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(userGet);
    }

    @PutMapping()
    public ResponseEntity<Void> updateUser(@RequestBody @Valid UserRequestPut userRequest){
        var user = userMapper.toUserPut(userRequest);
        userService.update(user);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.detete(id);
        return ResponseEntity.noContent().build();
    }

}


