package caio.treinamento.inicio.commons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;

@Component
public class FileUtils {
    @Autowired
    private ResourceLoader resourceLoader;

    public String fileLoader(String nomeFile) throws IOException {
        var file = resourceLoader.getResource("classpath:%s".formatted(nomeFile)).getFile();
        return new String(Files.readAllBytes(file.toPath()));
    }
}
