package cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.model.domain;

import javax.persistence.*;

@Entity
@Table(name = "GAME_RESULT")
public class GameResult {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "dice_one")
    private int diceOne;

    @Column(name = "dice_two")
    private int diceTwo;

    @Column(name = "winner")
    private Boolean winner;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = true)
    private Player player;

    public GameResult() {
    }

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

    public void setDiceTwo(int diceTwp) {
        this.diceTwo = diceTwp;
    }

    public Boolean getWinner() {
        return winner;
    }

    public void setWinner(Boolean winner) {
        this.winner = winner;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
