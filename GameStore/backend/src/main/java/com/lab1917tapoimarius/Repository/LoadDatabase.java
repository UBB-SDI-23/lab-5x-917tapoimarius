//package com.lab1917tapoimarius.Repository;
//
//import com.lab1917tapoimarius.Exception.NotFoundException;
//import com.lab1917tapoimarius.Model.Customer;
//import com.lab1917tapoimarius.Model.Developer;
//import com.lab1917tapoimarius.Model.Game;
//import com.lab1917tapoimarius.Model.Transaction;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.Optional;
//
//@Configuration
//public class LoadDatabase {
//    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
//
//    @Bean
//    CommandLineRunner initDatabase(GameRepository gameRepository, DeveloperRepository developerRepository,CustomerRepository customerRepository, TransactionRepository transactionRepository) {
//        Developer d1 = new Developer("Rockstar Games", "New York, USA", "Take-Two Interactive", 1998, 63);
//        Developer d2 = new Developer("EA Sports", "Redwood City, USA", "EA Games", 1991, 7377);
//        Developer d3 = new Developer("SCS Software", "Prague, Czech Republic", "Pavel Sebor", 1997, 23);
//        Developer d4 = new Developer("Ubisoft Montreal", "Montreal, Canada", "Ubisoft", 1997, 2125);
//        Developer d5 = new Developer("Sumo Digital", "Sheffield, United Kingdom", "PlayStation PC LLC", 2003, 50);
//        Developer d6 = new Developer("Playground Games", "Leamington Spa, United Kingdom", "Xbox Game Studios", 2010, 95);
//        Developer d7 = new Developer("Hazelight Studios", "Stockholm, Sweden", "EA Games", 2014, 4);
//        Developer d8 = new Developer("CD PROJEKT RED", "Łódź, Poland", "CD PROJEKT RED", 2002, 40);
//        Developer d9 = new Developer("Avalanche Software", "Salt Lake City, USA", "Warner Bros. Games", 1995, 5);
//        Developer d10 = new Developer("BANDAI NAMCO Studios Inc.", "Tokyo, Japan", " BANDAI NAMCO Entertainment", 2012, 7);
//        Customer c1 = new Customer("Marius", "Tapoi", "tapoimarius@yahoo.com", "Bistrita, Romania", "+40740673612");
//        Customer c2 = new Customer("Andrei", "Radacina", "radacainandrei@gmail.com", "Bistrita, Romania", "+40740287286");
//        Customer c3 = new Customer("Dominic", "Cont", "contdominicclaudio@gmail.com", "Bistrita, Romania", "+40742386554");
//        Customer c4 = new Customer("Marian", "Anghel", "anghelmarian@yahoo.com", "Sibiu, Romania", "+40750882394");
//        Customer c5 = new Customer("Elias", "Gavriliuc", "elforc112@yahoo.com", "Landskrona, Sweden", "+46720327428");
//        Customer c6 = new Customer("Iannis", "Taravinas", "pleuzaverde@gmail.com", "Cluj-Napoca, Romania", "+40723884884");
//        Customer c7 = new Customer("Alex", "Windschar", "alexwindschar@gmail.com", "Waldkraiburg, Germany", "+497123456789");
//        Customer c8 = new Customer("Stefan", "Burlacu", "stefferandy@gmail.com", "Bucharest, Romania", "+40745781282");
//        Customer c9 = new Customer("Manuel", "Hermoso", "manuelhermoso@yahoo.com", "Madrid, Spain", "+34789224228");
//        Customer c10 = new Customer("Peter", "Gulacsi", "petergulacsi@yahoo.com", "Budapest, Hungary", "+36726889034");
//
//        Game g1 = new Game("GTA V", "action", "SP & MP", 2013, 29.99, d1);
//        Game g2 = new Game("RDR2", "action", "SP & MP", 2018, 59.99, d1);
//        Game g3 = new Game("FIFA 23", "sports", "SP & MP", 2022, 59.99, d2);
//        Game g4 = new Game("ETS2", "simulation", "SP & MP", 2012, 19.99, d3);
//        Game g5 = new Game("Tom Clancy's Rainbow Six Siege", "FPS", "SP & MP", 2015, 19.99, d4);
//        Game g6 = new Game("Sackboy: A Big Adventure", "Adventure", "SP & CO-OP", 2022, 59.99, d5);
//        Game g7 = new Game("Forza Horizon 5", "Racing", "MP", 2021, 59.99, d6);
//        Game g8 = new Game("It Takes Two", "Adventure", "CO-OP", 2021, 39.99, d7);
//        Game g9 = new Game("Cyberpunk 2077", "RPG", "SP", 2020, 59.99, d8);
//        Game g10 = new Game("Hogwarts Legacy", "Fantasy", "SP", 2023, 59.99, d9);
//        Game g11 = new Game("TEKKEN 7", "Fighting", "SP & MP", 2017, 39.99, d10);
//
//        return args -> {
//            log.info("Preloading " + developerRepository.save(d1));
//            log.info("Preloading " + developerRepository.save(d2));
//            log.info("Preloading " + developerRepository.save(d3));
//            log.info("Preloading " + developerRepository.save(d4));
//            log.info("Preloading " + developerRepository.save(d5));
//            log.info("Preloading " + developerRepository.save(d6));
//            log.info("Preloading " + developerRepository.save(d7));
//            log.info("Preloading " + developerRepository.save(d8));
//            log.info("Preloading " + developerRepository.save(d9));
//            log.info("Preloading " + developerRepository.save(d10));
//
//            log.info("Preloading " + customerRepository.save(c1));
//            log.info("Preloading " + customerRepository.save(c2));
//            log.info("Preloading " + customerRepository.save(c3));
//            log.info("Preloading " + customerRepository.save(c4));
//            log.info("Preloading " + customerRepository.save(c5));
//            log.info("Preloading " + customerRepository.save(c6));
//            log.info("Preloading " + customerRepository.save(c7));
//            log.info("Preloading " + customerRepository.save(c8));
//            log.info("Preloading " + customerRepository.save(c9));
//            log.info("Preloading " + customerRepository.save(c10));
//
//            log.info("Preloading " + gameRepository.save(g1));
//            log.info("Preloading " + gameRepository.save(g2));
//            log.info("Preloading " + gameRepository.save(g3));
//            log.info("Preloading " + gameRepository.save(g4));
//            log.info("Preloading " + gameRepository.save(g5));
//            log.info("Preloading " + gameRepository.save(g6));
//            log.info("Preloading " + gameRepository.save(g7));
//            log.info("Preloading " + gameRepository.save(g8));
//            log.info("Preloading " + gameRepository.save(g9));
//            log.info("Preloading " + gameRepository.save(g10));
//            log.info("Preloading " + gameRepository.save(g11));
//
//            log.info("Preloading " + transactionRepository.save(new Transaction(g1, c1, "digital", 1)));
//            log.info("Preloading " + transactionRepository.save(new Transaction(g2, c1, "digital", 2)));
//            log.info("Preloading " + transactionRepository.save(new Transaction(g1, c2, "digital", 1)));
//            log.info("Preloading " + transactionRepository.save(new Transaction(g3, c3, "physical", 3)));
//            log.info("Preloading " + transactionRepository.save(new Transaction(g4, c4, "digital", 1)));
//            log.info("Preloading " + transactionRepository.save(new Transaction(g5, c5, "digital", 5)));
//            log.info("Preloading " + transactionRepository.save(new Transaction(g6, c6, "physical", 20)));
//            log.info("Preloading " + transactionRepository.save(new Transaction(g7, c7, "physical", 4)));
//            log.info("Preloading " + transactionRepository.save(new Transaction(g8, c8, "digital", 7)));
//            log.info("Preloading " + transactionRepository.save(new Transaction(g9, c9, "physical", 9)));
//            log.info("Preloading " + transactionRepository.save(new Transaction(g9, c9, "digital", 15)));
//            log.info("Preloading " + transactionRepository.save(new Transaction(g10, c10, "digital", 10)));
//
//        };
//    }
//}
