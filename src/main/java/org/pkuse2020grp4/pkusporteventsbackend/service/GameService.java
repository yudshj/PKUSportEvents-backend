package org.pkuse2020grp4.pkusporteventsbackend.service;

import org.pkuse2020grp4.pkusporteventsbackend.entity.Game;
import org.pkuse2020grp4.pkusporteventsbackend.entity.Team;
import org.pkuse2020grp4.pkusporteventsbackend.repo.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class GameService {
    @Autowired
    GameRepository gameRepository;

    public Game getGameById(int gameId)
    {
        return gameRepository.findGameByGameId(gameId);
    }

    public List<Game> getAllGames()
    {
        Sort sort = Sort.by(Sort.Direction.DESC, "gameId");
        return gameRepository.findAll(sort);
    }

    public List<Game> getGamesByMark(int mark)
    {
        Sort sort = Sort.by(Sort.Direction.DESC, "gameId");
        return gameRepository.findAllByMark(mark,sort);
    }

    public void addGame(Game game)
    {
        try {
/*
            System.out.println(game.toString());
            Set<Team> teams=game.getTeams();
            for (Team t:
                 teams) {
                System.out.println(t.toString());
            }
*/
            gameRepository.save(game);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
