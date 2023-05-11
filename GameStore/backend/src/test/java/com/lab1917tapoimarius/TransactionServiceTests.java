package com.lab1917tapoimarius;
import com.lab1917tapoimarius.Model.*;
import com.lab1917tapoimarius.Repository.GameRepository;
import com.lab1917tapoimarius.Repository.TransactionRepository;
import com.lab1917tapoimarius.Service.GameService;
import com.lab1917tapoimarius.Service.TransactionService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTests {
    @Mock
    TransactionRepository transactionRepository;
    @InjectMocks
    private TransactionService transactionService;

    @Test
    public void testGetGameWithPriceHigherThanGivenValue(){
        Developer d1 = new Developer("Rockstar Games", "New York, USA", "Take-Two Interactive", 1998, 63);
        Developer d2 = new Developer("EA Sports", "Redwood City, USA", "EA Games", 1991, 7377);

        Game g1 = new Game("GTA V", "action", "SP & MP", 2013, 29.99, d1,"");
        Game g2 = new Game("RDR2", "action", "SP & MP", 2018, 59.99, d1, "");
        Game g3 = new Game("FIFA 23", "sports", "SP & MP", 2022, 59.99, d2, "");

        Customer c1 = new Customer("Marius", "Tapoi", "tapoimarius@yahoo.com", "Bistrita, Romania", "+40740673612");
        Customer c2 = new Customer("Andrei", "Radacina", "radacainandrei@gmail.com", "Bistrita, Romania", "+40740287286");
        Customer c3 = new Customer("Dominic", "Cont", "contdominicclaudio@gmail.com", "Bistrita, Romania", "+40742386554");

        Transaction t1 = new Transaction(g1, c1, "digital", 1);
        Transaction t2 = new Transaction(g2, c1, "digital", 2);
        Transaction t3 = new Transaction(g1, c2, "digital", 1);
        Transaction t4 = new Transaction(g3, c3, "physical", 3);

        List<Transaction> transactionList = Arrays.asList(t1, t2, t3, t4);

        when(transactionRepository.findAll()).thenReturn(transactionList);

        TransactionDTO t1dto = new TransactionDTO("Marius", "Tapoi", 149.97);
        TransactionDTO t2dto = new TransactionDTO("Andrei", "Radacina", 29.99);
        TransactionDTO t3dto = new TransactionDTO("Dominic", "Cont", 179.97);

        List<TransactionDTO> transactionDTOList = Arrays.asList(t3dto, t1dto, t2dto);
        List<TransactionDTO> requiredTransactions = transactionService.getCustomersOrderedByMoneySpent(0);
        assertSame(transactionDTOList.get(0).getFirstname(), requiredTransactions.get(0).getFirstname());
        assertSame(transactionDTOList.get(1).getFirstname(), requiredTransactions.get(1).getFirstname());
        assertSame(transactionDTOList.get(2).getFirstname(), requiredTransactions.get(2).getFirstname());
    }
}
