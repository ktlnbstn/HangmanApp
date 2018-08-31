package org.ktlnbstn.hangmanapp.model.data;

import org.ktlnbstn.hangmanapp.model.Game;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface GameDAO extends CrudRepository<Game, Integer> {

        Iterable<Game> findAllByOrderByScoreDesc();

        ArrayList<Game> findByDifficultyOrderByScoreDesc(Integer difficulty);

        @Override
        Optional findById(Integer integer);

}
