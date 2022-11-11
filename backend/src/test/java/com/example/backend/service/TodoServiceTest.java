package com.example.backend.service;

import com.example.backend.model.Todo;
import com.example.backend.repo.TodoRepo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class TodoServiceTest {

    TodoService todoService = new TodoService(new TodoRepo(new ArrayList<>()));
    @Test
    void listtodoes_expect_emptyList() {
        List<Todo> expList = new ArrayList<>();

        List<Todo> result = todoService.listTodos();

        assertEquals(expList,result);
    }

    @Test
    void gettodoById_expect_NoSuchElementException() {

        String giveId = "0";

        assertThrows(NoSuchElementException.class, () -> todoService.getTodoById(giveId));
    }

    @Test
    void savetodo_expect_valid_todo() {
        Todo givenTodo = new Todo("","test","OPEN");
        Todo result = todoService.saveTodo(givenTodo);

        assertInstanceOf(Todo.class,result);
        assertFalse(result.id().isEmpty());
        assertFalse(result.id().isBlank());
    }

    @Test
    void updatetodo_expect_NoSuchElementException() {
        String giveId = "0";
        Todo givenTodo = new Todo("","test","OPEN");

        assertThrows(NoSuchElementException.class, () -> todoService.updateTodo(giveId,givenTodo));
    }

    @Test
    void deletetodo_expect_NoSuchElementException() {
        String giveId = "0";
        assertThrows(NoSuchElementException.class, () -> todoService.deleteTodo(giveId));
    }
}