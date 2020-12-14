package org.pkuse2020grp4.pkusporteventsbackend.controller;

import org.pkuse2020grp4.pkusporteventsbackend.entity.Game;
import org.pkuse2020grp4.pkusporteventsbackend.service.GameService;
import org.pkuse2020grp4.pkusporteventsbackend.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GameController {
    @Autowired
    private GameService gameService;

    @PostMapping("/api/game/get/{id}")
    public Result get(@PathVariable("id") int gameId) {
        return Result.buildSuccessResult("success", gameId + "data");
    }

    @PostMapping("/api/game/getByMark/{mark}")
    public Result getGamesByMark(@PathVariable("mark") int mark){
        List<Game> games=gameService.getGamesByMark(mark);
        return Result.buildSuccessResult("Get games with mark", games);
    }

    @PostMapping("/api/game/getAll")
    public Result getAllGames(){
        List<Game> games = gameService.getAllGames();
        return Result.buildSuccessResult("Get all games", games);
    }

    @PostMapping("/api/game/add")
    public Result addGame(@RequestBody Game game){
        gameService.addGame(game);
        return Result.buildSuccessResult("Successfully added game.");
    }

}
