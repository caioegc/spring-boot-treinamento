package caio.treinamento.inicio.config;

import external.dependency.Conections;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@RequiredArgsConstructor
public class ConnectionConfig {
    private final ConnectionConfigurationProperties connectionConfigurationProperties;

    @Value("${database.url}")
    private String url;

    @Value("${database.username}")
    private String username;

    @Value("${database.password}")
    private String password;

    @Bean
    @Profile("mysql")
    public Conections conectionsMySql() {
        return new Conections(
                connectionConfigurationProperties.url(),
                connectionConfigurationProperties.username(),
                connectionConfigurationProperties.password());
    }

    @Bean
    @Profile("mongo")
    public Conections conectionsMongoDB() {
        return new Conections(
                connectionConfigurationProperties.url(),
                connectionConfigurationProperties.username(),
                connectionConfigurationProperties.password());
    }
}
