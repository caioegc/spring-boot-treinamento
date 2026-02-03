package caio.treinamento.inicio.mapper;

import caio.treinamento.inicio.entity.User;
import caio.treinamento.inicio.response.UserResponse;
import caio.treinamento.request.UserRequest;
import caio.treinamento.request.UserRequestPut;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;
import java.util.Map;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    @Mapping(target = "id", expression = "java(java.util.concurrent.ThreadLocalRandom.current().nextLong(1000))")
    User toUser(UserRequest user);

    User toUserPut(UserRequestPut user);

    List<UserResponse> toUserGet(List<User> userResponse);

    UserResponse toUserGet(User user);




}
