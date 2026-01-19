package caio.treinamento.inicio.config;

import external.dependency.Conections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ConnectionConfig {

    @Bean
   public Conections conectionsMySql(){
       return new Conections("caio", "caioegc", "321");
   }

    @Bean
    @Primary
    public Conections conectionsMongoDB(){
        return new Conections("caio", "caioegc", "321");
    }
}
