package com.lab1917tapoimarius.Repository;

import com.lab1917tapoimarius.Model.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface DeveloperRepository extends JpaRepository<Developer, Long> {
}
