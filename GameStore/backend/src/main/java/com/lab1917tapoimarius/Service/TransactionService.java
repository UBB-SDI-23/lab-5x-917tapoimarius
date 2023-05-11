package com.lab1917tapoimarius.Service;

import com.lab1917tapoimarius.Exception.NotFoundException;
import com.lab1917tapoimarius.Model.Customer;
import com.lab1917tapoimarius.Model.Transaction;
import com.lab1917tapoimarius.Model.TransactionDTO;
import com.lab1917tapoimarius.Repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class TransactionService extends EntityService<Transaction> {
    public TransactionService(TransactionRepository repository) {
        super(repository);
    }
    public void update(Transaction newTransaction, Long id){
        repository.findById(id).map(transaction -> {
            transaction.setFormat(newTransaction.getFormat());
            transaction.setQuantity(newTransaction.getQuantity());
            transaction.setGame(newTransaction.getGameEntity());
            transaction.setCustomer(newTransaction.getCustomerEntity());
            return repository.save(transaction);
        }).orElseGet(()->{
            newTransaction.setId(id);
            return repository.save(newTransaction);
        });
    }

//    public List<TransactionDTO> getCustomersOrderedByMoneySpent(Integer pageNumber){
//        return ((TransactionRepository) repository)
//    }

    public List<TransactionDTO> getCustomersOrderedByMoneySpent(Integer pageNumber){
        return ((TransactionRepository)repository).findAllByCustomerEntityOrderByCheckoutDesc(
                PageRequest.of(pageNumber, 10)
        );
    }
}
