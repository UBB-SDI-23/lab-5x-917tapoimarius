package com.lab1917tapoimarius.Service;

import com.lab1917tapoimarius.Exception.NotFoundException;
import com.lab1917tapoimarius.Model.Developer;
import com.lab1917tapoimarius.Model.Game;
import com.lab1917tapoimarius.Repository.DeveloperRepository;
import com.lab1917tapoimarius.Repository.GameRepository;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GameService extends EntityService<Game> {
    @Autowired
    public GameService(GameRepository repository) {
        super(repository);
    }

    public List<Long> getAllGameIds(){
        return repository.findAll().stream().map(game -> game.getId()).collect(Collectors.toList());
    }

    public List<Game> getGamesByNameGenrePrice(String query){
        //Get the first 20 results
        PageRequest pageRequest = PageRequest.of(0, 20);
        //Cast the repository to GameRepository to access specific methods
        GameRepository gameRepository = (GameRepository)repository;

        String[] queryFields = query.split(" ");
        Integer length = queryFields.length;

        String name = queryFields[0];
        if(length < 2)
            return gameRepository.getGamesByNameIgnoreCase(name, pageRequest);

        String genre = Arrays.stream(queryFields).skip(1).
                filter(s -> !StringUtils.isNumeric(s)).collect(Collectors.joining(" "));
        if(!StringUtils.isNumeric(queryFields[length-1]))
            return gameRepository.getGamesByNameIgnoreCaseAndGenreIgnoreCase(name, genre, pageRequest);

        Double price = Double.parseDouble(queryFields[length - 1]);
        return gameRepository.getGamesByNameIgnoreCaseAndGenreIgnoreCaseAndPrice(
                name, genre, price, pageRequest
        );
    }

    public void update(Game newGame, Long id){
        repository.findById(id).map(game -> {
            game.setName(newGame.getName());
            game.setGenre(newGame.getGenre());
            game.setModes(newGame.getModes());
            game.setYearOfRelease(newGame.getYearOfRelease());
            game.setPrice(newGame.getPrice());
            game.setDeveloper(newGame.getDeveloperEntity());
            game.setDescription(newGame.getDescription());
            return repository.save(game);
        }).orElseGet(()->{
            newGame.setId(id);
            return repository.save(newGame);
        });
    }
    public List<Game> getGameWithPriceHigherThanGivenValue(Double price, Integer pageNumber){
        return ((GameRepository)repository).findByPriceGreaterThan(price, PageRequest.of(pageNumber, 10));
    }
//
//    public List<Game> addMultipleGames(List<Game> gameRequests, long id){
//        List<Game> games = new ArrayList<>();
//        Developer developer = developerRepository.findById(id).orElseThrow(() ->new NotFoundException(id));
//        for (Game gameRequest : gameRequests) {
//            Game game = new Game(gameRequest.getName(), gameRequest.getGenre(), gameRequest.getModes(), gameRequest.getYearOfRelease(),
//                    gameRequest.getPrice(), developer);
//            game = gameRepository.save(game);
//            games.add(game);
//        }
//        return games;
//    }
}
