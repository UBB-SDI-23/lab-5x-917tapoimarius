package com.lab1917tapoimarius;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.assertj.core.api.Assertions.assertThat;

import com.lab1917tapoimarius.Controller.Controller;
import com.lab1917tapoimarius.Model.Developer;
import com.lab1917tapoimarius.Model.Game;
import com.lab1917tapoimarius.Service.GameService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.testng.annotations.BeforeClass;
//import org.junit.runner.RunWith;
//import org.junit.runners.JUnit4;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@ComponentScan(basePackages = {"com.lab1917tapoimarius"})
@SpringBootTest
@AutoConfigureMockMvc
class ControllerTests {

    private static List<Game> gameList;
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameService gameService;


    @BeforeAll
    public static void init(){
        Developer d1 = new Developer("Rockstar Games", "New York, USA", "Take-Two Interactive", 1998, 63);
        Developer d2 = new Developer("EA Sports", "Redwood City, USA", "EA Games", 1991, 7377);

        Game g1 = new Game("GTA V", "action", "SP & MP", 2013, 29.99, d1);
        Game g2 = new Game("RDR2", "action", "SP & MP", 2018, 59.99, d1);
        Game g3 = new Game("FIFA 23", "sports", "SP & MP", 2022, 59.99, d2);

        gameList = Arrays.asList(g2, g3);

    }

    @Test
    public void contextLoads(){
        assertThat(gameService).isNotNull();
    }

    @Test
    public void testGetGameWithPriceHigherThanGivenValue() throws Exception{
//        Developer d1 = new Developer("Rockstar Games", "New York, USA", "Take-Two Interactive", 1998, 63);
//        Developer d2 = new Developer("EA Sports", "Redwood City, USA", "EA Games", 1991, 7377);
//
//        Game g1 = new Game("GTA V", "action", "SP & MP", 2013, 29.99, d1);
//        Game g2 = new Game("RDR2", "action", "SP & MP", 2018, 59.99, d1);
//        Game g3 = new Game("FIFA 23", "sports", "SP & MP", 2022, 59.99, d2);
//
//        gameList = Arrays.asList(g2, g3);

        when(gameService.getGameWithPriceHigherThanGivenValue(40d)).thenReturn(gameList);
        this.mockMvc.perform(get("/games/getWithPriceHigherThan/40"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("RDR2")));
    }

    @Test
    public void testGetGameWithPriceHigherThanGivenValueNotFound() throws Exception{
//        Developer d1 = new Developer("Rockstar Games", "New York, USA", "Take-Two Interactive", 1998, 63);
//        Developer d2 = new Developer("EA Sports", "Redwood City, USA", "EA Games", 1991, 7377);
//
//        Game g1 = new Game("GTA V", "action", "SP & MP", 2013, 29.99, d1);
//        Game g2 = new Game("RDR2", "action", "SP & MP", 2018, 59.99, d1);
//        Game g3 = new Game("FIFA 23", "sports", "SP & MP", 2022, 59.99, d2);
//
//        gameList = Arrays.asList(g2, g3);

        when(gameService.getGameWithPriceHigherThanGivenValue(40d)).thenReturn(gameList);
        this.mockMvc.perform(get("/games/getWithPriceHigherThan/"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
