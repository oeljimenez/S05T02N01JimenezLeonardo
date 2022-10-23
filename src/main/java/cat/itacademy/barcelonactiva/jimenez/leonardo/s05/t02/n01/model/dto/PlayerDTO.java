package cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class PlayerDTO {
    @ApiModelProperty(notes = "Product ID", example = "1", required = false)
    private String id;
    @ApiModelProperty(notes = "Player name", example = "Leo", required = false)
    private String name;
    @ApiModelProperty(notes = "Registration Date", example = "2022-10-14 18:21:34")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm", timezone = "Europe/Madrid")
    private Date registrationDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm", timezone = "Europe/Madrid")
    @ApiModelProperty(notes = "Player success rate", example = "25.25")
    private Double successRate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public Double getSuccessRate() {
        return successRate;
    }

    public void setSuccessRate(Double successRate) {
        this.successRate = successRate;
    }
}
