package caio.treinamento.inicio.response;

import lombok.*;


@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserResponse {

        @EqualsAndHashCode.Include
        private Long id;

        private String primeiroNome;
        private String ultimoNome;
        private String email;
    }

