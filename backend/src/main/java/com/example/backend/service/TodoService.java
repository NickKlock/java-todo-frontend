package com.example.backend.service;

import com.example.backend.model.Todo;
import com.example.backend.repo.TodoRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class TodoService {
    private TodoRepo todoRepo;

    public TodoService(TodoRepo todoRepo) {
        this.todoRepo = todoRepo;
    }

    public List<Todo> listTodos() {
        return todoRepo.getTodos();
    }

    public Todo getTodoById(String id) {
        return todoRepo.getById(id);
    }

    public Todo saveTodo(Todo todo) {
        String randomId = String.valueOf(generateRandom());
        Todo withId = new Todo(randomId,todo.description(),todo.status());
        return todoRepo.addTodo(withId);
    }

    public Todo updateTodo(String id, Todo todo) {
        return todoRepo.updateTodo(id, todo);
    }

    public String deleteTodo(String id) {
        return todoRepo.delete(id);
    }

    private int generateRandom(){
        return ThreadLocalRandom.current().nextInt(1, 1000);
    }
}
