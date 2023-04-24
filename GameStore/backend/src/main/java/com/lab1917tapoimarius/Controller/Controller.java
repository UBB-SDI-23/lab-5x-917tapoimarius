package com.lab1917tapoimarius.Controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.lab1917tapoimarius.Exception.NotFoundException;
import com.lab1917tapoimarius.Model.*;
import com.lab1917tapoimarius.Repository.GameRepository;
import com.lab1917tapoimarius.Service.CustomerService;
import com.lab1917tapoimarius.Service.DeveloperService;
import com.lab1917tapoimarius.Service.GameService;
import com.lab1917tapoimarius.Service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        return new ResponseEntity<>(developerService.getDeveloperById(id), HttpStatus.OK);
    }
    @GetMapping("/developers/")
    public ResponseEntity<List<Developer>> getAllDeveloper(){
        //List<Long> developersIdList = developerService.getAllDeveloper().stream().map(Developer::getId).collect(Collectors.toList());
        //return developersIdList;
        return new ResponseEntity<>(developerService.getAllDeveloper(), HttpStatus.OK);
    }

    @PostMapping("/developers/")
    public ResponseEntity<Object> addDeveloper(@RequestBody Developer newDeveloper){
        developerService.addDeveloper(newDeveloper);
        return new ResponseEntity<>("Developer added successfully!", HttpStatus.CREATED);
    }

    @PutMapping("/developers/{id}")
    public ResponseEntity<Object> updateDeveloper(@RequestBody Developer newDeveloper, @PathVariable Long id){
        developerService.updateDeveloper(newDeveloper, id);
        return new ResponseEntity<>("Developer updated successfully!", HttpStatus.OK);
    }

    @DeleteMapping("/developers/{id}")
    public ResponseEntity<Object> deleteDeveloper(@PathVariable Long id){
        developerService.deleteDeveloper(id);
        return new ResponseEntity<>("Developer deleted successfully!", HttpStatus.OK);
    }

    @GetMapping("/games/{id}")
    public ResponseEntity<Object> getGameById(@PathVariable Long id) {
        return new ResponseEntity<>(gameService.getGameById(id), HttpStatus.OK);
    }
    @GetMapping("/games/")
    public ResponseEntity<List<Game>> getAllGames(){
//        List<Long> gameIdList = gameService.getAllGames().stream().map(Game::getId).collect(Collectors.toList());
//        return gameIdList;
        return new ResponseEntity<>(gameService.getAllGames(), HttpStatus.OK);
    }

    @PostMapping("/games/")
    public ResponseEntity<Object> addGame(@RequestBody Game newGame){
        gameService.addGame(newGame);
        return new ResponseEntity<>("Game added successfully!", HttpStatus.CREATED);
    }

    @PutMapping("/games/{id}")
    public ResponseEntity<Object> updateGame(@RequestBody Game newGame, @PathVariable Long id){
        gameService.updateGame(newGame, id);
        return new ResponseEntity<>("Game updated successfully!", HttpStatus.OK);
    }

    @DeleteMapping("/games/{id}")
    public ResponseEntity<Object> deleteGame(@PathVariable Long id){
        gameService.deleteGame(id);
        return new ResponseEntity<>("Game deleted successfully!", HttpStatus.OK);
    }

    @GetMapping("/games/getWithPriceHigherThan/{price}")
    public ResponseEntity<Object> getGameWithPriceHigherThanGivenValue(@PathVariable Double price){
        return new ResponseEntity<>(gameService.getGameWithPriceHigherThanGivenValue(price), HttpStatus.OK);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Object> getCustomerById(@PathVariable Long id) {
        return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.OK);
    }
    @GetMapping("/customers/")
    public ResponseEntity<List<Customer>> getAllCustomer(){
//        List<Long> customersIdList = customerService.getAllCustomer().stream().map(Customer::getId).collect(Collectors.toList());
//        return customersIdList;
        return new ResponseEntity<>(customerService.getAllCustomer(), HttpStatus.OK);
    }

    @PostMapping("/customers/")
    public ResponseEntity<Object> addCustomer(@Valid @RequestBody Customer newCustomer){
        customerService.addCustomer(newCustomer);
        return ResponseEntity.ok("Customer added successfully!");
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<Object> updateCustomer(@RequestBody Customer newCustomer, @PathVariable Long id){
        customerService.updateCustomer(newCustomer, id);
        return new ResponseEntity<>("Customer updated successfully!", HttpStatus.OK);
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomer(id);
        return new ResponseEntity<>("Customer deleted successfully!", HttpStatus.OK);
    }

    @GetMapping("/transactions/{id}")
    public ResponseEntity<Object> getTransactionById(@PathVariable Long id) {
        return new ResponseEntity<>(transactionService.getTransactionById(id), HttpStatus.OK);
    }
    @GetMapping("/transactions/")
    public ResponseEntity<List<Transaction>> getAllTransaction(){
//        List<Long> transactionsIdList = transactionService.getAllTransaction().stream().map(Transaction::getId).collect(Collectors.toList());
//        return transactionsIdList;
        return new ResponseEntity<>(transactionService.getAllTransaction(), HttpStatus.OK);
    }

    @PostMapping("/transactions/")
    public ResponseEntity<Object> addTransaction(@RequestBody Transaction newTransaction){
        transactionService.addTransaction(newTransaction);
        return new ResponseEntity<>("Transaction added successfully!", HttpStatus.CREATED);
    }

    @PutMapping("/transactions/{id}")
    public ResponseEntity<Object> updateTransaction(@RequestBody Transaction newTransaction, @PathVariable Long id){
        transactionService.updateTransaction(newTransaction, id);
        return new ResponseEntity<>("Transaction updated successfully!", HttpStatus.OK);
    }

    @DeleteMapping("/transactions/{id}")
    public ResponseEntity<Object> deleteTransaction(@PathVariable Long id){
        transactionService.deleteTransaction(id);
        return new ResponseEntity<>("Transaction deleted successfully!", HttpStatus.OK);
    }

    @GetMapping("/customersSpent/")
    public List<TransactionDTO> customersSpent() {
        List<TransactionDTO> customersSpentList = transactionService.getCustomersOrderedByMoneySpent();
        return customersSpentList;
    }
    @GetMapping("/customerSpendingByDeveloper/{spent}")
    public List<CustomerSpendingByDeveloperDTO> getCustomerSpendingByDeveloperReport(@PathVariable Double spent) {
        List<CustomerSpendingByDeveloperDTO> CustomerSpendingByDeveloperList = customerService.getCustomerSpendingByDeveloperReport(transactionService, spent);
        return CustomerSpendingByDeveloperList;
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

    @PostMapping("/developers/{id}/game/")
    public ResponseEntity<List<Game>> addMultipleGames(@RequestBody List<Game> gameRequest, @PathVariable long id){
        List<Game> games = gameService.addMultipleGames(gameRequest, id);
        return ResponseEntity.ok().body(games);
    }
}
