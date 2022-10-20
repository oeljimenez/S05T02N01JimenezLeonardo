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

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {

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
        Player player = playerRepository.findById(id).get();
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
        gameResultRepository.save(gameResult);

        updateSuccessRate(player);
    }

    @Override
    public OptionalDouble getPlayersRanking() {
        return playerRepository.findAll().stream().filter(x -> x.getSuccessRate() != null)
                .mapToDouble(Player::getSuccessRate).average();
    }

    @Override
    public Player getLoser() {
        return playerRepository.findAll().stream().filter(x -> x.getSuccessRate() != null)
                .min(Comparator.comparing(Player::getSuccessRate)).get();
    }

    @Override
    public Player getWinner() {
        return playerRepository.findAll().stream().filter(x -> x.getSuccessRate() != null)
                .max(Comparator.comparing(Player::getSuccessRate)).get();
    }

    public void updateSuccessRate(Player player) {
        List<GameResult> gameResults = gameResultRepository.findByPlayer(player);
        if (gameResults.size() > 0) {
            int hits = gameResults.stream().filter(g -> Boolean.TRUE.equals(g.getWinner()))
                    .collect(Collectors.toList()).size();

            Double percentage = (double) hits / gameResults.size() * 100.00;
            player.setSuccessRate(percentage);
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
