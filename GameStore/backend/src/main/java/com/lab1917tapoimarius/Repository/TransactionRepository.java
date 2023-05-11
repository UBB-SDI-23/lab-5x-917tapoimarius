package com.lab1917tapoimarius.Repository;

import com.lab1917tapoimarius.Model.Transaction;
import com.lab1917tapoimarius.Model.TransactionDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT new com.lab1917tapoimarius.Model.TransactionDTO(c.firstname, c.lastname, sum(s.price)) " +
            "FROM Transaction t INNER JOIN t.customerEntity c INNER JOIN t.game s " +
            "GROUP BY c.id, c.firstname, c.lastname, c.phoneNumber " +
            "ORDER BY sum(s.price) DESC")
    List<TransactionDTO> findAllByCustomerEntityOrderByCheckoutDesc(Pageable pageable);
}