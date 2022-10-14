package cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.controllers;


import cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.model.domain.Player;
import cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.model.dto.PlayerDTO;
import cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.model.services.PlayerService;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/players")
public class PlayerController {
    Logger logger = (Logger) LoggerFactory.getLogger(PlayerController.class);

    @Autowired
    PlayerService playerService;

    @PostMapping("/add")
    public ResponseEntity<PlayerDTO> add(@RequestBody PlayerDTO playerDTO) {
        logger.info("Calling add method");
        try {
            Player player = playerService.add(playerService.convertToEntity(playerDTO));
            return new ResponseEntity<>(playerService.convertToDto(player), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PlayerDTO> update(@PathVariable("id") long id, @RequestBody PlayerDTO playerDetails) {
        logger.info("Calling update method");
        try {
            Optional<Player> player = playerService.findById(id);
            if (id > 0 && player.isPresent()) {
                player.get().setName(playerDetails.getName());
                player.get().setRegistrationDate(new Date());
                playerService.update(player.get());
                return new ResponseEntity<>(playerService.convertToDto(playerService.findById(id).get()),
                        HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<PlayerDTO> delete(@PathVariable("id") long id) {
        logger.info("Calling delete method");
        try {
            Optional<Player> player = playerService.findById(id);
            if (id > 0 && player.isPresent()) {
                playerService.deleteById(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<PlayerDTO> getOne(@PathVariable("id") long id) {
        logger.info("Calling getOne method");
        try {
            Optional<Player> player = playerService.findById(id);
            if (id > 0 && player.isPresent()) {
                playerService.findById(id);
                return new ResponseEntity<>(playerService.convertToDto(playerService.findById(id).get()),
                        HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<PlayerDTO>> getAll() {
        logger.info("Calling getAll method");
        try {
            return new ResponseEntity<>(playerService.getAll().stream().map(
                    s -> playerService.convertToDto(s)).collect(Collectors.toList()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
