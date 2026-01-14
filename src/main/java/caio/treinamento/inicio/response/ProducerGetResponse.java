package caio.treinamento.inicio.response;


import caio.treinamento.inicio.entity.Produtor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class ProducerGetResponse {
   private Long id;
   private String nome;
   private LocalDateTime createdAt;
   public static List<Produtor> list = new ArrayList<>();

}
