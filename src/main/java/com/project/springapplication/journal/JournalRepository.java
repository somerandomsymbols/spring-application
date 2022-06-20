package com.project.springapplication.journal;

import org.springframework.data.repository.CrudRepository;

public interface JournalRepository extends CrudRepository<Journal, Integer> {
    public Long countById(int id);
}
