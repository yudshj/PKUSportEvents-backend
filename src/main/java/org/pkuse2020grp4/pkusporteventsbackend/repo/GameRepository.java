package org.pkuse2020grp4.pkusporteventsbackend.repo;

import org.pkuse2020grp4.pkusporteventsbackend.entity.Game;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Integer> {
    List<Game> findAllByMark(Integer mark, Sort sort);
    Game findGameByGameId(int gameId);
}
