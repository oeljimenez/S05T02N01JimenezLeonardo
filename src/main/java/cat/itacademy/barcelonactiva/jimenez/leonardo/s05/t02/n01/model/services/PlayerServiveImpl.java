package cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.model.services;

import cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.S05T01N01JimenezLeonardoSpringConfig;
import cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.model.domain.Player;
import cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.model.dto.PlayerDTO;
import cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.model.repository.PlayerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiveImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    ApplicationContext ctx = new AnnotationConfigApplicationContext(S05T01N01JimenezLeonardoSpringConfig.class);

    @Override
    public Player add(Player player) {
        return playerRepository.save(player);
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

    public PlayerDTO convertToDto(Player player) {
        PlayerDTO playerDTO = ctx.getBean(ModelMapper.class).map(player, PlayerDTO.class);
        return playerDTO;
    }

    public Player convertToEntity(PlayerDTO playerDTO) {
        Player player = ctx.getBean(ModelMapper.class).map(playerDTO, Player.class);
        System.out.println("converto entity-->" + playerDTO);

        if (playerDTO.getId() != null) {
            System.out.println("converto entity-->" + playerDTO);
            player.setName(playerDTO.getName());
            player.setRegistrationDate(playerDTO.getRegistrationDate());
        }

        return player;
    }

}
