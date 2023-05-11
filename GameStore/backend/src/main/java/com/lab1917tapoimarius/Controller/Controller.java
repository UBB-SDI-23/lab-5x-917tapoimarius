package com.lab1917tapoimarius.Controller;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

import com.lab1917tapoimarius.Model.*;
import com.lab1917tapoimarius.Service.CustomerService;
import com.lab1917tapoimarius.Service.DeveloperService;
import com.lab1917tapoimarius.Service.GameService;
import com.lab1917tapoimarius.Service.TransactionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:80")
@RequestMapping("/api")
@RestController
public class Controller {
    @Autowired
    private DeveloperService developerService;
    @Autowired
    private GameService gameService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private TransactionService transactionService;

    public Controller(DeveloperService developerService, GameService gameService, CustomerService customerService, TransactionService transactionService) {
        this.developerService = developerService;
        this.gameService = gameService;
        this.customerService = customerService;
        this.transactionService = transactionService;
    }

    @GetMapping("/developers/{id}")
    public ResponseEntity<Object> getDeveloperById(@PathVariable Long id) {
        return new ResponseEntity<>(developerService.getById(id), HttpStatus.OK);
    }
    @GetMapping("/developers/byPage/{pageNumber}")
    public ResponseEntity<List<Developer>> getAllDeveloper(@PathVariable Integer pageNumber){
        //List<Long> developersIdList = developerService.getAllDeveloper().stream().map(Developer::getId).collect(Collectors.toList());
        //return developersIdList;
        return new ResponseEntity<>(developerService.getAll(pageNumber), HttpStatus.OK);
    }

    @GetMapping("/developers/count")
    public ResponseEntity<Object> getDevelopersCount(){
        return new ResponseEntity<>(developerService.getCount(), HttpStatus.OK);
    }
    @GetMapping("/developers/autocomplete")
    public ResponseEntity<Object> getDeveloperByNameHq(@RequestParam(name="query") String query){
        return new ResponseEntity<>(developerService.getDeveloperByNameHq(
                URLDecoder.decode(query, StandardCharsets.UTF_8)), HttpStatus.OK);
    }

    @PostMapping("/developers/")
    public ResponseEntity<Object> addDeveloper(@RequestBody Developer newDeveloper, BindingResult result){
        if(result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors().stream().map(e -> e.getDefaultMessage())
                    .map(Objects::toString).collect(Collectors.joining("\n")), HttpStatus.BAD_REQUEST);
        }
        developerService.add(newDeveloper);
        return new ResponseEntity<>("Developer added successfully!", HttpStatus.CREATED);
    }

    @PutMapping("/developers/{id}")
    public ResponseEntity<Object> updateDeveloper(@RequestBody Developer newDeveloper, @PathVariable Long id, BindingResult result){
        if(result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors().stream().map(e -> e.getDefaultMessage())
                    .map(Objects::toString).collect(Collectors.joining("\n")), HttpStatus.BAD_REQUEST);
        }
        developerService.update(newDeveloper, id);
        return new ResponseEntity<>("Developer updated successfully!", HttpStatus.OK);
    }

    @DeleteMapping("/developers/{id}")
    public ResponseEntity<Object> deleteDeveloper(@PathVariable Long id){
        developerService.delete(id);
        return new ResponseEntity<>("Developer deleted successfully!", HttpStatus.OK);
    }

    @GetMapping("/games/{id}")
    public ResponseEntity<Object> getGameById(@PathVariable Long id) {
        return new ResponseEntity<>(gameService.getById(id), HttpStatus.OK);
    }
    @GetMapping("/games/byPage/{pageNumber}")
    public ResponseEntity<List<Game>> getAllGames(@PathVariable Integer pageNumber){
//        List<Long> gameIdList = gameService.getAllGames().stream().map(Game::getId).collect(Collectors.toList());
//        return gameIdList;
        return new ResponseEntity<>(gameService.getAll(pageNumber), HttpStatus.OK);
    }
    @GetMapping("/games/autocomplete")
    public ResponseEntity<Object> getGamesByNameGenrePrice(@RequestParam(name="query") String query){
        return new ResponseEntity<>(gameService.getGamesByNameGenrePrice(
                URLDecoder.decode(query, StandardCharsets.UTF_8)), HttpStatus.OK);
    }
    @GetMapping("/games/count")
    public ResponseEntity<Object> getGamesCount(){
        return new ResponseEntity<>(gameService.getCount(), HttpStatus.OK);
    }

    @PostMapping("/games/")
    public ResponseEntity<Object> addGame(@RequestBody Game newGame, BindingResult result){
        if(result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors().stream().map(e -> e.getDefaultMessage())
                    .map(Objects::toString).collect(Collectors.joining("\n")), HttpStatus.BAD_REQUEST);
        }
        gameService.add(newGame);
        return new ResponseEntity<>("Game added successfully!", HttpStatus.CREATED);
    }

    @PutMapping("/games/{id}")
    public ResponseEntity<Object> updateGame(@RequestBody Game newGame, @PathVariable Long id, BindingResult result){
        if(result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors().stream().map(e -> e.getDefaultMessage())
                    .map(Objects::toString).collect(Collectors.joining("\n")), HttpStatus.BAD_REQUEST);
        }
        gameService.update(newGame, id);
        return new ResponseEntity<>("Game updated successfully!", HttpStatus.OK);
    }

    @DeleteMapping("/games/{id}")
    public ResponseEntity<Object> deleteGame(@PathVariable Long id){
        gameService.delete(id);
        return new ResponseEntity<>("Game deleted successfully!", HttpStatus.OK);
    }

    @GetMapping("/games/getWithPriceHigherThan/{pageNumber}")
    public ResponseEntity<Object> getGameWithPriceHigherThanGivenValue(@PositiveOrZero @PathVariable Integer pageNumber, @RequestParam Double price){
        return new ResponseEntity<>(gameService.getGameWithPriceHigherThanGivenValue(price, pageNumber), HttpStatus.OK);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Object> getCustomerById(@PathVariable Long id) {
        return new ResponseEntity<>(customerService.getById(id), HttpStatus.OK);
    }
    @GetMapping("/customers/byPage/{pageNumber}")
    public ResponseEntity<List<Customer>> getAllCustomers(@PathVariable Integer pageNumber){
//        List<Long> customersIdList = customerService.getAllCustomer().stream().map(Customer::getId).collect(Collectors.toList());
//        return customersIdList;
        return new ResponseEntity<>(customerService.getAll(pageNumber), HttpStatus.OK);
    }

    @GetMapping("/customers/count")
    public ResponseEntity<Object> getCustomersCount(){
        return new ResponseEntity<>(customerService.getCount(), HttpStatus.OK);
    }

    @GetMapping("/customers/autocomplete")
    public ResponseEntity<Object> getCustomersByFirstNameLastNameEmail(@RequestParam(name="query") String query){
        return new ResponseEntity<>(customerService.getCustomersByFirstNameLastNameEmail(
                URLDecoder.decode(query, StandardCharsets.UTF_8)), HttpStatus.OK);
    }

    @PostMapping("/customers/")
    public ResponseEntity<Object> addCustomer(@Valid @RequestBody Customer newCustomer, BindingResult result){
        if(result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors().stream().map(e -> e.getDefaultMessage())
                    .map(Objects::toString).collect(Collectors.joining("\n")), HttpStatus.BAD_REQUEST);
        }
        customerService.add(newCustomer);
        return ResponseEntity.ok("Customer added successfully!");
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<Object> updateCustomer(@Valid @RequestBody Customer newCustomer, @PathVariable Long id, BindingResult result){
        if(result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors().stream().map(e -> e.getDefaultMessage())
                    .map(Objects::toString).collect(Collectors.joining("\n")), HttpStatus.BAD_REQUEST);
        }
        customerService.update(newCustomer, id);
        return new ResponseEntity<>("Customer updated successfully!", HttpStatus.OK);
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable Long id){
        customerService.delete(id);
        return new ResponseEntity<>("Customer deleted successfully!", HttpStatus.OK);
    }

    @GetMapping("/transactions/{id}")
    public ResponseEntity<Object> getTransactionById(@PathVariable Long id) {
        return new ResponseEntity<>(transactionService.getById(id), HttpStatus.OK);
    }
    @GetMapping("/transactions/byPage/{pageNumber}")
    public ResponseEntity<List<Transaction>> getAllTransaction(@PathVariable Integer pageNumber){
//        List<Long> transactionsIdList = transactionService.getAllTransaction().stream().map(Transaction::getId).collect(Collectors.toList());
//        return transactionsIdList;
        return new ResponseEntity<>(transactionService.getAll(pageNumber), HttpStatus.OK);
    }

    @PostMapping("/transactions/")
    public ResponseEntity<Object> addTransaction(@RequestBody Transaction newTransaction, BindingResult result){
        if(result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors().stream().map(e -> e.getDefaultMessage())
                    .map(Objects::toString).collect(Collectors.joining("\n")), HttpStatus.BAD_REQUEST);
        }
        transactionService.add(newTransaction);
        return new ResponseEntity<>("Transaction added successfully!", HttpStatus.CREATED);
    }

    @GetMapping("/transactions/count")
    public ResponseEntity<Object> getTransactionsCount(){
        return new ResponseEntity<>(transactionService.getCount(), HttpStatus.OK);
    }

    @PutMapping("/transactions/{id}")
    public ResponseEntity<Object> updateTransaction(@RequestBody Transaction newTransaction, @PathVariable Long id, BindingResult result){
        if(result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors().stream().map(e -> e.getDefaultMessage())
                    .map(Objects::toString).collect(Collectors.joining("\n")), HttpStatus.BAD_REQUEST);
        }
        transactionService.update(newTransaction, id);
        return new ResponseEntity<>("Transaction updated successfully!", HttpStatus.OK);
    }

    @DeleteMapping("/transactions/{id}")
    public ResponseEntity<Object> deleteTransaction(@PathVariable Long id){
        transactionService.delete(id);
        return new ResponseEntity<>("Transaction deleted successfully!", HttpStatus.OK);
    }

    @GetMapping("/customersSpent")
    public ResponseEntity<Object> customersSpent(@RequestParam Integer pageNumber) {
        return new ResponseEntity<>(transactionService.getCustomersOrderedByMoneySpent(pageNumber), HttpStatus.OK);
    }
    @GetMapping("/customerSpendingByDeveloper/{spent}")
    public List<CustomerSpendingByDeveloperDTO> getCustomerSpendingByDeveloperReport(@PathVariable Double spent, @RequestParam Integer pageNumber) {
        return customerService.getCustomerSpendingByDeveloperReport(transactionService, spent, pageNumber);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

//    @PostMapping("/developers/{id}/game/")
//    public ResponseEntity<List<Game>> addMultipleGames(@RequestBody List<Game> gameRequest, @PathVariable long id){
//        List<Game> games = gameService.addMultipleGames(gameRequest, id);
//        return ResponseEntity.ok().body(games);
//    }
}
