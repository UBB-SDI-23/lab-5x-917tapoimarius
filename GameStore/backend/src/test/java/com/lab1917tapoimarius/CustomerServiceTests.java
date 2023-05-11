package com.lab1917tapoimarius;
import com.lab1917tapoimarius.Model.*;
import com.lab1917tapoimarius.Repository.CustomerRepository;
import com.lab1917tapoimarius.Repository.DeveloperRepository;
import com.lab1917tapoimarius.Repository.GameRepository;
import com.lab1917tapoimarius.Repository.TransactionRepository;
import com.lab1917tapoimarius.Service.CustomerService;
import com.lab1917tapoimarius.Service.GameService;
import com.lab1917tapoimarius.Service.TransactionService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CustomerServiceTests {
    @Mock
    CustomerRepository customerRepository;
    @Mock
    TransactionRepository transactionRepository;
    @Mock
    GameRepository gameRepository;
    @Mock
    DeveloperRepository developerRepository;
    @InjectMocks
    private CustomerService customerService;
    @InjectMocks
    private TransactionService transactionService;

    @Test
    public void testGetCustomerSpendingByDeveloperReport(){
        Developer d1 = new Developer("Rockstar Games", "New York, USA", "Take-Two Interactive", 1998, 63);
        d1.setId(1l);
        Developer d2 = new Developer("EA Sports", "Redwood City, USA", "EA Games", 1991, 7377);
        d2.setId(2l);

        Game g1 = new Game("GTA V", "action", "SP & MP", 2013, 29.99, d1, "");
        g1.setId(1l);
        Game g2 = new Game("RDR2", "action", "SP & MP", 2018, 59.99, d1, "");
        g2.setId(2l);
        Game g3 = new Game("FIFA 23", "sports", "SP & MP", 2022, 59.99, d2, "");
        g3.setId(3l);

        Customer c1 = new Customer("Marius", "Tapoi", "tapoimarius@yahoo.com", "Bistrita, Romania", "+40740673612");
        c1.setId(1l);
        Customer c2 = new Customer("Andrei", "Radacina", "radacainandrei@gmail.com", "Bistrita, Romania", "+40740287286");
        Customer c3 = new Customer("Dominic", "Cont", "contdominicclaudio@gmail.com", "Bistrita, Romania", "+40742386554");
        c2.setId(2l);
        c3.setId(3l);

        Transaction t1 = new Transaction(g1, c1, "digital", 1);
        Transaction t2 = new Transaction(g2, c1, "digital", 2);
        Transaction t3 = new Transaction(g1, c2, "digital", 1);
        Transaction t4 = new Transaction(g3, c3, "physical", 3);
        t1.setId(1l);
        t1.setId(2l);
        t1.setId(3l);
        t1.setId(4l);

        List<Customer> customerList = Arrays.asList(c1, c2, c3);

        List<Customer> customerList2 = Arrays.asList(c3, c2, c1);

        when(customerRepository.findAll()).thenReturn(customerList);

        when(customerRepository.findAll(Sort.by("lastname").ascending())).thenReturn(customerList2);

        List<Transaction> transactionList = Arrays.asList(t1, t2, t3, t4);

        when(transactionRepository.findAll()).thenReturn(transactionList);

        List<Game> gameList = Arrays.asList(g1, g2, g3);

        when(gameRepository.findAll()).thenReturn(gameList);

        List<Developer> devList = Arrays.asList(d1, d2);

        when(developerRepository.findAll()).thenReturn(devList);

        when(transactionService.getAll(0)).thenReturn(transactionList);

        CustomerSpendingByDeveloperDTO c1dto = new CustomerSpendingByDeveloperDTO("Marius Tapoi", "Tapoi", 149.97);
        CustomerSpendingByDeveloperDTO c2dto = new CustomerSpendingByDeveloperDTO("Andrei Radacina", "Radacina", 29.99);
        CustomerSpendingByDeveloperDTO c3dto = new CustomerSpendingByDeveloperDTO("Dominic Cont", "Cont", 179.96);

        List<CustomerSpendingByDeveloperDTO> customerSpendingByDeveloperDTOList = Arrays.asList(c3dto, c1dto);
        List<CustomerSpendingByDeveloperDTO> requiredCustomers = customerService.getCustomerSpendingByDeveloperReport(transactionService, 100d, 0);
        assertEquals(customerSpendingByDeveloperDTOList.get(0).getCustomerName(), requiredCustomers.get(0).getCustomerName());
        assertEquals(customerSpendingByDeveloperDTOList.get(1).getCustomerName(), requiredCustomers.get(1).getCustomerName());
        assertFalse(Arrays.asList(requiredCustomers).contains(c2dto));
    }
}
