package cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.model.services;

import cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.model.domain.GameResult;
import cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.model.dto.GameResultDTO;

import java.util.List;

public interface GameResultService {
     public void deleteGameResults(Long id);
     public List<GameResult> getPlayerGameResults(Long id);

     public GameResultDTO convertToDto(GameResult gameResult);

     public GameResult convertToEntity(GameResultDTO gameResultDTO);
}

