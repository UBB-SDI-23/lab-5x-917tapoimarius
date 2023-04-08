package com.lab1917tapoimarius.Repository;

import com.lab1917tapoimarius.Model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
