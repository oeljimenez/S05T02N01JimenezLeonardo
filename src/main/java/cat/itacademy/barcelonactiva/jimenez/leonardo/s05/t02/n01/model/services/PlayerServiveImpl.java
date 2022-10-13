package cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.model.services;

import cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.model.domain.Player;
import cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.model.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiveImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public void add(Player player) {
        playerRepository.save(player);
    }

    @Override
    public void update(Player player) {
        playerRepository.save(player);
    }

    @Override
    public void deleteById(Long id) {
        playerRepository.deleteById(id);
    }

    public Optional<Player> findById(Long id) {
       return playerRepository.findById(id);
    }

    public List<Player> getAll() {
        return playerRepository.findAll();
    }

}
