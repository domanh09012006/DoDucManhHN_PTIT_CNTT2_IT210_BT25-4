package org.example.demo.service;

import org.example.demo.entity.Todo;
import org.example.demo.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository repo;

    public TodoService(TodoRepository repo) {
        this.repo = repo;
    }

    public List<Todo> getAll() {
        return repo.findAll();
    }

    public void save(Todo todo) {
        repo.save(todo);
    }
}