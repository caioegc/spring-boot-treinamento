package caio.treinamento.inicio.config;

import external.dependency.Conections;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
public class ConnectionConfig {
    @Value("${database.url}")
    private String url;

    @Value("${database.username}")
    private String username;

    @Value("${database.password}")
    private String password;

    @Bean
    @Profile("mysql")
   public Conections conectionsMySql(){
       return new Conections(url, username  , password);
   }

    @Bean
    @Profile("mongo")
    public Conections conectionsMongoDB(){
        return new Conections(url, username, password);
    }
}
