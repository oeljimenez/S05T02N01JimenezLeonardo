package cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.model.services;

import cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.model.domain.Player;
import cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.model.dto.PlayerDTO;

import java.util.List;
import java.util.Optional;

public interface PlayerService {
    public Player add(Player player);

    public void update(Player player);

    public void deleteById(Long id);

    public Optional<Player> findById(Long id);

    public List<Player> getAll();

    public Player findByName(String name);

    public PlayerDTO convertToDto(Player post);

    public Player convertToEntity(PlayerDTO post);

}

