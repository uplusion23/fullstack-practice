package com.uplusion23.todoServer.Repositories;

import com.uplusion23.todoServer.Models.Todo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Long> {
    public Iterable<Todo> findAllByUserId(Long userId);
}
