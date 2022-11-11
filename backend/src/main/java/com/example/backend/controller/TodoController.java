package com.example.backend.controller;

import com.example.backend.model.Todo;
import com.example.backend.service.TodoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/todo")
    public List<Todo> allTodo(){
        return this.todoService.listTodos();
    }

    @GetMapping("/todo/{id}")
    public Todo byId(@PathVariable String id){
        return this.todoService.getTodoById(id);
    }

    @PostMapping("/todo")
    public Todo newTodo(@RequestBody Todo todo){
        return this.todoService.saveTodo(todo);
    }

    @PutMapping("/todo/{id}")
    public Todo updateTodo(@PathVariable String id, @RequestBody Todo todo){
        return this.todoService.updateTodo(id, todo);
    }

    @DeleteMapping("/todo/{id}")
    public String deleteTodo(@PathVariable String id){
        return this.todoService.deleteTodo(id);
    }
}
