package cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.controllers;


import cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.model.domain.Player;
import cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.model.dto.PlayerDTO;
import cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.model.services.PlayerService;
import ch.qos.logback.classic.Logger;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@CrossOrigin()
public class PlayerController {
    Logger logger = (Logger) LoggerFactory.getLogger(PlayerController.class);

    @Autowired
    PlayerService playerService;

    @ApiOperation(value = "Create new player", notes = "Returns the created player")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created"),
            @ApiResponse(code = 500, message = "Error when creating player")
    })
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody PlayerDTO playerDTO) {
        logger.info("Calling create method");
        try {
            if (playerService.findByName(playerDTO.getName()) != null) {
                return new ResponseEntity<String>("Player's name should be unique, please use another name",
                        HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                Player player = playerService.add(playerService.convertToEntity(playerDTO));
                return new ResponseEntity<>(playerService.convertToDto(player), HttpStatus.CREATED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @ApiOperation(value = "Update new player", notes = "Returns the updated player")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created"),
            @ApiResponse(code = 400, message = "Player not found")
    })
    @PutMapping()
    public ResponseEntity<PlayerDTO> update(@RequestBody PlayerDTO playerDetails) {
        logger.info("Calling update method");
        try {
            Long idPlayer = playerDetails.getId();
            Optional<Player> player = playerService.findById(idPlayer);
            if (player.isPresent()) {
                player.get().setName(playerDetails.getName());
                playerService.update(player.get());
                return new ResponseEntity<>(playerService.convertToDto(playerService.findById(idPlayer).get()),
                        HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Player roll dice", notes = "Returns the result")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created"),
            @ApiResponse(code = 500, message = "Error when creating player")
    })
    @PostMapping("/{id}/games")
    public ResponseEntity<PlayerDTO> playDice(@PathVariable("id") long id) {
        logger.info("Calling playDice method");
        try {
            Player player = new Player();
            return new ResponseEntity<>(playerService.convertToDto(player), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Delete player by id", notes = "Return ok")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted"),
            @ApiResponse(code = 400, message = "Player not found")
    })
    @DeleteMapping("/{id}/games")
    public ResponseEntity<PlayerDTO> deletePlayerGames(@PathVariable("id") long id) {
        logger.info("Calling deletePlayerGames method");
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

    @ApiOperation(value = "Delete player by id", notes = "Return ok")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted"),
            @ApiResponse(code = 400, message = "Player not found")
    })
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

    @ApiOperation(value = "Get one player by id", notes = "Return the player information")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully found"),
            @ApiResponse(code = 400, message = "Player not found")
    })
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

    @ApiOperation(value = "Get all players", notes = "Return the player list")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully found"),
            @ApiResponse(code = 500, message = "Error when retrieving players")
    })
    @GetMapping()
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
