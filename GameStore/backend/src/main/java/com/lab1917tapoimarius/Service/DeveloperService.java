package com.lab1917tapoimarius.Service;

import com.lab1917tapoimarius.Exception.NotFoundException;
import com.lab1917tapoimarius.Model.Developer;
import com.lab1917tapoimarius.Repository.DeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DeveloperService {
    @Autowired
    private DeveloperRepository developerRepository;

    public DeveloperService(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    public Developer getDeveloperById(Long id) {
        return developerRepository.findById(id).orElseThrow(() ->new NotFoundException(id));
    }
    public List<Developer> getAllDeveloper(){
        return developerRepository.findAll();
    }

    public void addDeveloper(Developer newDeveloper){
        developerRepository.save(newDeveloper);
    }

    public void updateDeveloper(Developer newDeveloper, Long id){
        developerRepository.findById(id).map(display -> {
            display.setName(newDeveloper.getName());
            display.setHq(newDeveloper.getHq());
            display.setPublisher(newDeveloper.getPublisher());
            display.setFoundedIn(newDeveloper.getFoundedIn());
            display.setRevenue(newDeveloper.getRevenue());
            return developerRepository.save(display);
        }).orElseGet(()->{
            newDeveloper.setId(id);
            return developerRepository.save(newDeveloper);
        });
    }

    public void deleteDeveloper(Long id){
        developerRepository.deleteById(id);
    }
}
