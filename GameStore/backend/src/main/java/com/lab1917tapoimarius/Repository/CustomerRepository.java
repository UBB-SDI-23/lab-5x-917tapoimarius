package com.lab1917tapoimarius.Repository;

import com.lab1917tapoimarius.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
