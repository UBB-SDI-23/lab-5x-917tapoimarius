package com.lab1917tapoimarius.Service;

import com.lab1917tapoimarius.Exception.NotFoundException;
import com.lab1917tapoimarius.Model.Customer;
import com.lab1917tapoimarius.Model.CustomerSpendingByDeveloperDTO;
import com.lab1917tapoimarius.Model.Developer;
import com.lab1917tapoimarius.Model.Transaction;
import com.lab1917tapoimarius.Repository.CustomerRepository;
import com.lab1917tapoimarius.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(() ->new NotFoundException(id));
    }
    public List<Customer> getAllCustomer(){
        return customerRepository.findAll();
    }

    public void addCustomer(Customer newCustomer){
        customerRepository.save(newCustomer);
    }

    public void updateCustomer(Customer newCustomer, Long id){
        customerRepository.findById(id).map(display -> {
            display.setFirstname(newCustomer.getFirstname());
            display.setLastname(newCustomer.getLastname());
            display.setEmail(newCustomer.getEmail());
            display.setAddress(newCustomer.getAddress());
            display.setPhoneNumber(newCustomer.getPhoneNumber());
            return customerRepository.save(display);
        }).orElseGet(()->{
            newCustomer.setId(id);
            return customerRepository.save(newCustomer);
        });
    }

    public void deleteCustomer(Long id){
        customerRepository.deleteById(id);
    }

    public List<CustomerSpendingByDeveloperDTO> getCustomerSpendingByDeveloperReport(TransactionService transactionService, Double spent){
        List<CustomerSpendingByDeveloperDTO> reportData = new ArrayList<>();
        List<Customer> customers = customerRepository.findAll(Sort.by("lastname").ascending());
        List<Transaction> transactionsList = transactionService.getAllTransaction();

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
