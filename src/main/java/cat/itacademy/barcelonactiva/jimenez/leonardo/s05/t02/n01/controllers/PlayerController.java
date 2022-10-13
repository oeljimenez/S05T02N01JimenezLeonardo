package cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.controllers;


import cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.model.domain.Player;
import cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.model.services.PlayerService;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/players")
public class PlayerController {
    Logger logger = (Logger) LoggerFactory.getLogger(PlayerController.class);

    @Autowired
    PlayerService playerService;

    @PostMapping("/add")
    public ResponseEntity<Player> add(@RequestBody Player player) {
        logger.info("Calling add method");
        try {
            playerService.add(player);
            return new ResponseEntity<>(player, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Player> update(@PathVariable("id") long id, @RequestBody Player playerDetails) {
        logger.info("Calling update method");
        try {
            Optional<Player> player = playerService.findById(id);
            if (player.isPresent()) {
                player.get().setName(playerDetails.getName());
                playerService.update(player.get());
                return new ResponseEntity<>(playerService.findById(id).get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Player> delete(@PathVariable("id") long id) {
        logger.info("Calling delete method");
        try {
            Optional<Player> player = playerService.findById(id);
            if (player.isPresent()) {
                playerService.deleteById(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<Player> getOne(@PathVariable("id") long id) {
        logger.info("Calling getOne method");
        try {
            Optional<Player> player = playerService.findById(id);
            if (player.isPresent()) {
                playerService.deleteById(id);
                return new ResponseEntity<>(player.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Player>> getAll() {
        logger.info("Calling getAll method");
        try {
            List<Player> fruites = playerService.getAll();
            return new ResponseEntity<>(fruites, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
