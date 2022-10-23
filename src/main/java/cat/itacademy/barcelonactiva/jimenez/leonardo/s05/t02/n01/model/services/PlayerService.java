package cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.model.services;

import cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.model.domain.Player;
import cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.model.dto.PlayerDTO;

import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

public interface PlayerService {
    public Player add(Player player) throws Exception;

    public void update(Player player);

    public void deleteById(String id);

    public Optional<Player> findById(String id);

    public List<Player> getAll();

    public void playDice(String id);

    public OptionalDouble getPlayersRanking();

    public Player getLoser();

    public Player getWinner();

    public PlayerDTO convertToDto(Player player);

    public Player convertToEntity(PlayerDTO playerDTO);

}

