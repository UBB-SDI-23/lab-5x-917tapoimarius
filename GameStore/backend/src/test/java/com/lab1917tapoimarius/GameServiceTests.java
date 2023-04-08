package com.lab1917tapoimarius;
import com.lab1917tapoimarius.Model.Developer;
import com.lab1917tapoimarius.Model.Game;
import com.lab1917tapoimarius.Repository.GameRepository;
import com.lab1917tapoimarius.Service.GameService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class GameServiceTests {
    @Mock
    GameRepository gameRepository;
    @InjectMocks
    private GameService gameService;

    @Test
    public void testGetGameWithPriceHigherThanGivenValue(){
        Developer d1 = new Developer("Rockstar Games", "New York, USA", "Take-Two Interactive", 1998, 63);
        Developer d2 = new Developer("EA Sports", "Redwood City, USA", "EA Games", 1991, 7377);

        Game g1 = new Game("GTA V", "action", "SP & MP", 2013, 29.99, d1);
        Game g2 = new Game("RDR2", "action", "SP & MP", 2018, 59.99, d1);
        Game g3 = new Game("FIFA 23", "sports", "SP & MP", 2022, 59.99, d2);


        List<Game> smartphoneList = Arrays.asList(g1, g2, g3);

        when(gameRepository.findAll()).thenReturn(smartphoneList);

        List<Game> requiredGames = gameService.getGameWithPriceHigherThanGivenValue(40d);
        assertThat(requiredGames).contains(g2);
        assertThat(requiredGames).contains(g3);
    }
}
