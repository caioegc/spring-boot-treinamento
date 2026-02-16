package caio.treinamento.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class HeroePutRequest {

    @NotNull(message = "Id não pode ser nulo")
    private Long id;
    @NotBlank(message = "nome não pode ficar em branco")
    private String nome;
}
