package caio.treinamento.inicio;

import caio.treinamento.inicio.config.ConnectionConfigurationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = ConnectionConfigurationProperties.class)
public class InicioApplication {

    public static void main(String[] args) {
        SpringApplication.run(InicioApplication.class, args);
    }

}
