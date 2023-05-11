package com.lab1917tapoimarius.Repository;

import com.lab1917tapoimarius.Model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> getGamesByNameIgnoreCase(String name, Pageable pageable);
    List<Game> getGamesByNameIgnoreCaseAndGenreIgnoreCase(String name, String genre, Pageable pageable);
    List<Game> getGamesByNameIgnoreCaseAndGenreIgnoreCaseAndPrice(String name, String genre, Double price, Pageable pageable);

    List<Game> findByPriceGreaterThan(Double prince, Pageable pageable);
}
