package cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.model.services;

import cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.model.domain.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerService {
    public void add(Player player);

    public void update(Player player);

    public void deleteById(Long id);

    public Optional<Player> findById(Long id);

    public List<Player> getAll();

}

