package cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.model.services;

import cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.model.domain.GameResult;
import cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.model.domain.Player;
import cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.model.dto.GameResultDTO;
import cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.model.dto.PlayerDTO;

import java.util.List;
import java.util.Optional;

public interface GameResultService {
     public List<GameResult> getPlayerGameResults(Long id);

     public GameResultDTO convertToDto(GameResult gameResult);

     public GameResult convertToEntity(GameResultDTO gameResultDTO);
}

