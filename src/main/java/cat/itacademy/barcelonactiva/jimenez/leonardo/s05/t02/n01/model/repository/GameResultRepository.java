package cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.model.repository;

import cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.model.domain.GameResult;
import cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.model.domain.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameResultRepository extends MongoRepository<GameResult, String> {
    List<GameResult> findByPlayer(Player player);

}
