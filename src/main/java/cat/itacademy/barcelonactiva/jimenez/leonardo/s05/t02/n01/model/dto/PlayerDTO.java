package cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.model.dto;

import java.util.Date;

public class PlayerDTO {
    private Long id;
    private String name;
    private Date registrationDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}
