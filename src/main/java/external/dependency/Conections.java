package external.dependency;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
public class Conections {
    private String host;
    private String username;
    private String password;
}
