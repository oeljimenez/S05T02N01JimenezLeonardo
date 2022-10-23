package cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.model.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@ToString
@Document(collection = "Player")
public class Player {

    @Id
    private String id;

    private String name;
    @CreatedDate
    private Date registrationDate;

    private Double successRate;

}
