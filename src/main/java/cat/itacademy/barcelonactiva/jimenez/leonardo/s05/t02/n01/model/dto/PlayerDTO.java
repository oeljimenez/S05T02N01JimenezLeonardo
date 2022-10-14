package cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.model.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class PlayerDTO {
    @ApiModelProperty(notes = "Product ID", example = "1", required = false)
    private Long id;
    @ApiModelProperty(notes = "Player name", example = "Leo", required = false)
    private String name;
    @ApiModelProperty(notes = "Registration Date", example = "2022-10-14 18:21:34", required = false)
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
