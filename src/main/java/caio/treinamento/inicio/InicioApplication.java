package caio.treinamento.inicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan(basePackages = "caio")
public class InicioApplication {
    public static void main(String[] args) {
        SpringApplication.run(InicioApplication.class, args);
    }
}
