package caio.treinamento.inicio.controller;


import caio.treinamento.inicio.entity.Anime;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1")
public class HelloController {

    @GetMapping("hi")
    public String sayHello(){
        return "VAMO QUE VAMO CAINHO!!!";
    }
}
