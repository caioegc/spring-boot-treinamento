package caio.treinamento.inicio.mapper;

import caio.treinamento.inicio.entity.Profile;
import caio.treinamento.inicio.request.ProfilePostRequest;
import caio.treinamento.inicio.request.ProfilePutRequest;
import caio.treinamento.inicio.response.ProfileGetResponse;
import caio.treinamento.inicio.response.ProfilePostResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProfileMapper {

    List<ProfileGetResponse> list(List<Profile> profile);
    ProfileGetResponse listGet(Profile profile);
    ProfilePostResponse createdResponse(Profile profile);
    Profile createdRequest(ProfilePostRequest profile);
    Profile updateRequest(ProfilePutRequest profile);
}