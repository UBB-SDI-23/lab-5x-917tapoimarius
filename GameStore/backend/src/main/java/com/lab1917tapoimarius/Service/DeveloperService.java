package com.lab1917tapoimarius.Service;

import com.lab1917tapoimarius.Model.Developer;
import com.lab1917tapoimarius.Repository.DeveloperRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DeveloperService extends EntityService<Developer> {

    @Autowired
    public DeveloperService(DeveloperRepository repository) {
        super(repository);
    }

    public List<Long> getAllDevelopersId() {
        return repository.findAll().stream().map(developer -> developer.getId()).collect(Collectors.toList());
    }
    public List<Developer> getDeveloperByNameHq(String query){
        String name, hq;
        String[] hqArray;

        DeveloperRepository developerRepository = (DeveloperRepository) repository;
        Pageable pageable = PageRequest.of(0, 20);

        name = query.split("[0-9]")[0].strip();
        String nameAndRestString = query.substring(name.length());
        hqArray = nameAndRestString.split(" ");
        if(hqArray.length < 2)
            return developerRepository.findByNameContainingIgnoreCase(name, pageable);

        hq = hqArray[1];

        return ((DeveloperRepository) repository).findByNameContainingIgnoreCaseAndHqContainingIgnoreCase(
                name, hq, PageRequest.of(0, 20));
    }

    public void update(Developer newDeveloper, Long id){
        repository.findById(id).map(display -> {
            display.setName(newDeveloper.getName());
            display.setHq(newDeveloper.getHq());
            display.setPublisher(newDeveloper.getPublisher());
            display.setFoundedIn(newDeveloper.getFoundedIn());
            display.setRevenue(newDeveloper.getRevenue());
            return repository.save(display);
        }).orElseGet(()->{
            newDeveloper.setId(id);
            return repository.save(newDeveloper);
        });
    }
}
