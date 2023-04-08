package com.lab1917tapoimarius.Repository;

import com.lab1917tapoimarius.Model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
