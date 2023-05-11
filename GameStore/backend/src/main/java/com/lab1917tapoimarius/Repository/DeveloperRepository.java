package com.lab1917tapoimarius.Repository;

import com.lab1917tapoimarius.Model.Developer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeveloperRepository extends JpaRepository<Developer, Long> {
    public List<Developer> findByNameContainingIgnoreCase(String name, Pageable pageable);

    public List<Developer> findByNameContainingIgnoreCaseAndHqContainingIgnoreCase(String name, String hq, Pageable pageable);
}
