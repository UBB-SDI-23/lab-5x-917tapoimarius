package com.lab1917tapoimarius.Service;

import com.lab1917tapoimarius.Exception.NotFoundException;
import com.lab1917tapoimarius.Model.Customer;
import com.lab1917tapoimarius.Model.CustomerSpendingByDeveloperDTO;
import com.lab1917tapoimarius.Model.Developer;
import com.lab1917tapoimarius.Model.Transaction;
import com.lab1917tapoimarius.Repository.CustomerRepository;
import com.lab1917tapoimarius.Repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerService extends EntityService<Customer>{
    @Autowired
    public CustomerService(CustomerRepository repository) {
        super(repository);
    }

    public List<Customer> getCustomersByFirstNameLastNameEmail(String query) {
        CustomerRepository customerRepository = (CustomerRepository) repository;
        PageRequest pageRequest = PageRequest.of(0, 20);

        String[] queryStrings = query.split(" ");
        Integer length = queryStrings.length;

        String firstName = queryStrings[0];
        if (length < 2)
            return customerRepository.findByFirstnameContainingIgnoreCase(firstName, pageRequest);

        String lastName = queryStrings[1];
        if (length < 3)
            return customerRepository.findByFirstnameContainingIgnoreCaseAndLastnameContainingIgnoreCase(firstName, lastName, pageRequest);

        String email = queryStrings[2];
        return customerRepository.findByFirstnameContainingIgnoreCaseAndLastnameContainingIgnoreCaseAndEmailContaining(
                firstName, lastName, email, pageRequest
        );
    }

    public void update(Customer newCustomer, Long id){
        repository.findById(id).map(display -> {
            display.setFirstname(newCustomer.getFirstname());
            display.setLastname(newCustomer.getLastname());
            display.setEmail(newCustomer.getEmail());
            display.setAddress(newCustomer.getAddress());
            display.setPhoneNumber(newCustomer.getPhoneNumber());
            return repository.save(display);
        }).orElseGet(()->{
            newCustomer.setId(id);
            return repository.save(newCustomer);
        });
    }

    public List<CustomerSpendingByDeveloperDTO> getCustomerSpendingByDeveloperReport(TransactionService transactionService, Double spent, Integer pageNumber){
        List<CustomerSpendingByDeveloperDTO> reportData = new ArrayList<>();
        List<Customer> customers = repository.findAll(Sort.by("lastname").ascending());
        List<Transaction> transactionsList = transactionService.getAll(pageNumber);

        for (Customer customer : customers) {
            List<Transaction> transactions = transactionsList.stream().filter(t -> Objects.equals(t.getCustomerEntity().getId(), customer.getId())).toList();
            Map<Developer, Double> spendingByDeveloper = new HashMap<>();

            for (Transaction transaction : transactions) {
                Double spending = transaction.getQuantity() * transaction.getGameEntity().getPrice();
                Developer developer = transaction.getGameEntity().getDeveloperEntity();
                spendingByDeveloper.put(developer, spendingByDeveloper.getOrDefault(developer, 0.0) + spending);
            }

            for (Map.Entry<Developer, Double> entry : spendingByDeveloper.entrySet()) {
                Developer developer = entry.getKey();
                Double totalSpending = entry.getValue();
                CustomerSpendingByDeveloperDTO dto = new CustomerSpendingByDeveloperDTO(
                        customer.getFirstname() + " " + customer.getLastname(),
                        developer.getName(),
                        totalSpending
                );
                if(dto.getTotalSpending() > spent)
                    reportData.add(dto);
            }
        }

        reportData.sort(Comparator.comparing(CustomerSpendingByDeveloperDTO::getDeveloperName));
        return reportData;
    }
}
