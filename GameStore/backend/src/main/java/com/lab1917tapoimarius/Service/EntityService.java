package com.lab1917tapoimarius.Service;

import com.lab1917tapoimarius.Exception.NotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class EntityService<T> {
    final int PAGE_SIZE = 10;

    protected JpaRepository<T, Long> repository;

    public EntityService(JpaRepository<T, Long> repository) {
        this.repository = repository;
    }

    public List<T> getAll(Integer pageNumber) {
        return repository.findAll(PageRequest.of(pageNumber, PAGE_SIZE)).getContent();
    }

    public T getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Long getCount() {
        return repository.count();
    }

    public void add(T newEntity) {
        repository.save(newEntity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    abstract public void update(T newEntity, Long id);
}