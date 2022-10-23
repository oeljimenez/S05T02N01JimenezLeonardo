package cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.model.services;

import cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.S05T01N01JimenezLeonardoSpringConfig;
import cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.model.domain.GameResult;
import cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.model.domain.Player;
import cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.model.dto.GameResultDTO;
import cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.model.repository.GameResultRepository;
import cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.model.repository.PlayerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameResultServiceImpl implements GameResultService {
    @Autowired
    private GameResultRepository gameResultRepository;
    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public void deleteGameResults(String id) {
        Player player = playerRepository.findById(id).get();
        gameResultRepository.findByPlayer(player).stream().forEach(gameResult -> {
            gameResultRepository.deleteById(gameResult.getId());
        });
        //deleting old success rating information
        player.setSuccessRate(null);
        playerRepository.save(player);
    }

    @Override
    public List<GameResult> getPlayerGameResults(String id) {
        return gameResultRepository.findByPlayer(playerRepository.findById(id).get());
    }

    ApplicationContext ctx = new AnnotationConfigApplicationContext(S05T01N01JimenezLeonardoSpringConfig.class);

    public GameResultDTO convertToDto(GameResult gameResult) {
        GameResultDTO gameResultDTO = ctx.getBean(ModelMapper.class).map(gameResult, GameResultDTO.class);
        return gameResultDTO;
    }

    public GameResult convertToEntity(GameResultDTO gameResultDTO) {
        GameResult gameResult = ctx.getBean(ModelMapper.class).map(gameResultDTO, GameResult.class);

        if (gameResultDTO.getId() != null) {
            gameResult.setDiceOne(gameResultDTO.getDiceOne());
            gameResult.setDiceTwo(gameResultDTO.getDiceTwo());
            gameResult.setWinner(gameResultDTO.getWinner());
        }

        return gameResult;
    }

}
