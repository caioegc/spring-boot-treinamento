package caio.treinamento.inicio.controller;

import external.dependency.Conections;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/connection")
@RequiredArgsConstructor
public class ConfigurationController {

    private final Conections conections;

    @GetMapping
    public ResponseEntity<Conections> getConections() {
        return ResponseEntity.ok(conections);
    }
}
