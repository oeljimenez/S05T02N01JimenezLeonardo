package cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.model.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@Document(collection = "GameResult")
public class GameResult {

    @Id
    private String id;

    private int diceOne;

    private int diceTwo;

    private Boolean winner;

    @DBRef
    private Player player;

}
