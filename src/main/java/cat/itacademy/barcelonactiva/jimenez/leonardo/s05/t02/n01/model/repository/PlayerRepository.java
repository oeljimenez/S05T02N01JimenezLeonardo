package cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.model.repository;

import cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.model.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository <Player, Long> {

    Player findByName(String name);
}
