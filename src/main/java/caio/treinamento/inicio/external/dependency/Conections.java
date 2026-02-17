package caio.treinamento.inicio.external.dependency;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "database")
public class Conections {

    private String url;
    private String username;
    private String password;
}
