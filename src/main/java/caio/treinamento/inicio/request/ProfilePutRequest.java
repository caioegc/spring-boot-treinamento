package caio.treinamento.inicio.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Builder
@Getter
@Setter
public class ProfilePutRequest {
    private Long id;
    private String nome;
    private String description;
}

