package caio.treinamento.inicio.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {


    @GetMapping("hi")
    public String sayHello(){
        return "VAMO QUE VAMO CAINHO!!!";
    }
}
