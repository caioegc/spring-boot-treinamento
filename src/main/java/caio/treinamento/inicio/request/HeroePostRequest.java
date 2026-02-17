package caio.treinamento.inicio.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HeroePostRequest {

    @NotBlank(message = "Não pode ficar nulo")
    private String nome;
}
