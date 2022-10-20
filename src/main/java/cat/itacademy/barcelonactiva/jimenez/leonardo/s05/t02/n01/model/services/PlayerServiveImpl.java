package cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.model.services;

import cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.S05T01N01JimenezLeonardoSpringConfig;
import cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.model.domain.GameResult;
import cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.model.domain.Player;
import cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.model.dto.PlayerDTO;
import cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.model.repository.GameResultRepository;
import cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.model.repository.PlayerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class PlayerServiveImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private GameResultRepository gameResultRepository;

    ApplicationContext ctx = new AnnotationConfigApplicationContext(S05T01N01JimenezLeonardoSpringConfig.class);

    private static final String ANONYMOUS_NAME = "ANÒNIM";

    @Override
    public Player add(Player player) {
        if (player.getName().isBlank()) {
            player.setName(ANONYMOUS_NAME);
        } else if (playerRepository.findByName(player.getName()) != null) {
            return null;
        }
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

    @Override
    public void playDice(Long id) {
        Optional<Player> optional = playerRepository.findById(id);
        if (optional.isPresent()) {
            System.out.println("playing dice service implentation");
            Player player = optional.get();
            GameResult gameResult = new GameResult();
            Random random = new Random();
            int diceOne = random.nextInt((7 - 1) + 1) + 1;
            gameResult.setDiceOne(diceOne);
            int diceTwo = random.nextInt((7 - 1) + 1) + 1;
            gameResult.setDiceTwo(diceTwo);
            if (diceOne + diceTwo == 7) {
                gameResult.setWinner(Boolean.TRUE);
            } else {
                gameResult.setWinner(Boolean.FALSE);
            }
            gameResult.setPlayer(player);
           // gameResultRepository.save(gameResult);

            updateRate(player);
        }
    }

    @Override
    public void deleteGameResults(Long id) {
        Optional<Player> optional = playerRepository.findById(id);
        if (optional.isPresent()) {
            Player player = optional.get();
            gameResultRepository.findByPlayer(player).stream().forEach(gameResult -> {
                gameResultRepository.deleteById(gameResult.getId());
            });
        }
    }

    public void updateRate(Player player) {
        List<GameResult> gameResults = gameResultRepository.findByPlayer(player);
        if(gameResults.size()>0){

            int hits = gameResults.stream().filter(g-> Boolean.TRUE.equals(g.getWinner()))
                    .collect(Collectors.toList()).size();

            Double ratedo = (double) hits / gameResults.size() * 100.00;

            player.setSuccessRate(ratedo);
        }

        playerRepository.save(player);
    }


    public PlayerDTO convertToDto(Player player) {
        PlayerDTO playerDTO = ctx.getBean(ModelMapper.class).map(player, PlayerDTO.class);
        return playerDTO;
    }

    public Player convertToEntity(PlayerDTO playerDTO) {
        Player player = ctx.getBean(ModelMapper.class).map(playerDTO, Player.class);

        if (playerDTO.getId() != null) {
            player.setName(playerDTO.getName());
            player.setRegistrationDate(playerDTO.getRegistrationDate());
        }

        return player;
    }

}
