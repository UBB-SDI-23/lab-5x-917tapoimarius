package com.lab1917tapoimarius.Service;

import com.lab1917tapoimarius.Exception.NotFoundException;
import com.lab1917tapoimarius.Model.Customer;
import com.lab1917tapoimarius.Model.Transaction;
import com.lab1917tapoimarius.Model.TransactionDTO;
import com.lab1917tapoimarius.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }
    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id).orElseThrow(() ->new NotFoundException(id));
    }
    public List<Transaction> getAllTransaction(){
        return transactionRepository.findAll();
    }

    public void addTransaction(Transaction newTransaction){
        transactionRepository.save(newTransaction);
    }

    public void updateTransaction(Transaction newTransaction, Long id){
        transactionRepository.findById(id).map(transaction -> {
            transaction.setFormat(newTransaction.getFormat());
            transaction.setQuantity(newTransaction.getQuantity());
            return transactionRepository.save(transaction);
        }).orElseGet(()->{
            newTransaction.setId(id);
            return transactionRepository.save(newTransaction);
        });
    }

    public void deleteTransaction(Long id){
        transactionRepository.deleteById(id);
    }

    public List<TransactionDTO> getCustomersOrderedByMoneySpent(){
        List<TransactionDTO> customerSpendingList = new ArrayList<>();

        // Group transactions by customer and sum the total amount spent
        Map<Customer, Double> customerTotalSpendingMap = transactionRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(Transaction::getCustomerEntity,
                        Collectors.summingDouble(t -> t.getGameEntity().getPrice() * t.getQuantity())));

        // Convert the map to a list of DTOs
        customerTotalSpendingMap.forEach((customer, totalSpending) ->
                customerSpendingList.add(new TransactionDTO(customer.getFirstname(),  customer.getLastname(),
                        totalSpending)));

        // Sort the list by total spending in descending order
        customerSpendingList.sort(Comparator.comparingDouble(TransactionDTO::getTotalSpent).reversed());

        return customerSpendingList;
    }
}
