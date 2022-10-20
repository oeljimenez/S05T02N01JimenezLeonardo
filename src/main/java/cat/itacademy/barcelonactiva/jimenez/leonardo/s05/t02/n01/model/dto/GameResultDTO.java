package cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.model.dto;

import io.swagger.annotations.ApiModelProperty;

public class GameResultDTO {

    @ApiModelProperty(notes = "Product ID", example = "1", required = false)
    private long id;

    @ApiModelProperty(notes = "Value Dice One", example = "2")
    private int diceOne;

    @ApiModelProperty(notes = "Value Dice Two", example = "5")
    private int diceTwo;

    @ApiModelProperty(notes = "Player game result", example = "true")
    private Boolean winner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDiceOne() {
        return diceOne;
    }

    public void setDiceOne(int diceOne) {
        this.diceOne = diceOne;
    }

    public int getDiceTwo() {
        return diceTwo;
    }

    public void setDiceTwo(int diceTwo) {
        this.diceTwo = diceTwo;
    }

    public Boolean getWinner() {
        return winner;
    }

    public void setWinner(Boolean winner) {
        this.winner = winner;
    }
}
