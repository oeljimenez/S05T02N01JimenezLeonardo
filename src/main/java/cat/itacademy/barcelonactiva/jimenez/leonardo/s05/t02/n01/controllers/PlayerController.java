package cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.controllers;


import cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.model.domain.Player;
import cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.model.dto.GameResultDTO;
import cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.model.dto.PlayerDTO;
import cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01.model.services.GameResultService;
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

import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/players")
@CrossOrigin()
public class PlayerController {
    Logger logger = (Logger) LoggerFactory.getLogger(PlayerController.class);

    @Autowired
    PlayerService playerService;

    @Autowired
    GameResultService gameResultService;

    @ApiOperation(value = "Create new player", notes = "Returns the created player")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created"),
            @ApiResponse(code = 500, message = "Error when creating player")
    })
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody PlayerDTO playerDTO) {
        logger.info("Calling create method");
        try {
            Player player = playerService.add(playerService.convertToEntity(playerDTO));
            if (player != null) {
                return new ResponseEntity<>(playerService.convertToDto(player), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<String>("Player's name is already registered, please use another name",
                        HttpStatus.INTERNAL_SERVER_ERROR);
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
            String idPlayer = playerDetails.getId();
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

    @ApiOperation(value = "Player roll dice", notes = "Return ok")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created"),
            @ApiResponse(code = 500, message = "Error when creating player")
    })
    @PostMapping("/{id}/games")
    public ResponseEntity<Void> playDice(@PathVariable("id") String id) {
        logger.info("Calling playDice method");
        try {
            Optional<Player> player = playerService.findById(id);
            if (player.isPresent()) {
                playerService.playDice(id);
                return new ResponseEntity<>(null, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Delete player games results by id", notes = "Return ok")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted"),
            @ApiResponse(code = 400, message = "Player not found")
    })
    @DeleteMapping("/{id}/games")
    public ResponseEntity<Void> deleteGameResults(@PathVariable("id") String id) {
        logger.info("Calling deleteGameResults method");
        try {
            Optional<Player> player = playerService.findById(id);
            if (player.isPresent()) {
                gameResultService.deleteGameResults(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Get all players with percentages", notes = "Return the player list")
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

    @ApiOperation(value = "Get Player Game Results", notes = "Return Game Results list")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully found"),
            @ApiResponse(code = 400, message = "Player not found")
    })
    @GetMapping("/{id}/games")
    public ResponseEntity<List<GameResultDTO>> getPlayerGameResults(@PathVariable("id") String id) {
        logger.info("Calling getAllGameResults method");
        try {
            Optional<Player> player = playerService.findById(id);
            if (player.isPresent()) {
                return new ResponseEntity<>(gameResultService.getPlayerGameResults(id).stream().map(
                        s -> gameResultService.convertToDto(s)).collect(Collectors.toList()), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Get players success average", notes = "Return the average percentage of successes")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully found"),
            @ApiResponse(code = 500, message = "Error when retrieving players")
    })
    @GetMapping("/ranking")
    public ResponseEntity<OptionalDouble> getPlayersRanking() {
        logger.info("Calling getPlayersRanking method");
        try {
            return new ResponseEntity<>(playerService.getPlayersRanking(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Get loser player", notes = "Return winner Player")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully found"),
            @ApiResponse(code = 500, message = "Error when retrieving players")
    })
    @GetMapping("/ranking/loser")
    public ResponseEntity<PlayerDTO> getLoser() {
        logger.info("Calling getLoser method");
        try {
            return new ResponseEntity<>(playerService.convertToDto(playerService.getLoser()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Get winner player", notes = "Return loser Player")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully found"),
            @ApiResponse(code = 500, message = "Error when retrieving players")
    })
    @GetMapping("/ranking/winner")
    public ResponseEntity<PlayerDTO> getWinner() {
        logger.info("Calling getWinner method");
        try {
            return new ResponseEntity<>(playerService.convertToDto(playerService.getWinner()), HttpStatus.OK);
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
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
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

    @ApiOperation(value = "Get one player by id", notes = "Return the player information")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully found"),
            @ApiResponse(code = 400, message = "Player not found")
    })
    @GetMapping("/getOne/{id}")
    public ResponseEntity<PlayerDTO> getOne(@PathVariable("id") String id) {
        logger.info("Calling getOne method");
        try {
            Optional<Player> player = playerService.findById(id);
            if (player.isPresent()) {
                playerService.findById(id);
                return new ResponseEntity<>(playerService.convertToDto(playerService.findById(id).get()),
                        HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
