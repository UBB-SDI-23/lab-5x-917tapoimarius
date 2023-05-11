package com.lab1917tapoimarius.Repository;

import com.lab1917tapoimarius.Model.Customer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByFirstnameContainingIgnoreCase(String firstname, Pageable pageable);

    List<Customer> findByFirstnameContainingIgnoreCaseAndLastnameContainingIgnoreCase(String firstname, String lastname, Pageable pageable);

    List<Customer> findByFirstnameContainingIgnoreCaseAndLastnameContainingIgnoreCaseAndEmailContaining(String firstname, String lastname,
                                                                                                                     String email, Pageable pageable);

}
