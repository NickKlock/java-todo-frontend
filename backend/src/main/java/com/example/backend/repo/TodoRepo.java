package com.example.backend.repo;

import com.example.backend.exceptions.ToDoNotFoundException;
import com.example.backend.model.Todo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Repository
public class TodoRepo {
    private List<Todo> todos;

    public TodoRepo(List<Todo> todos) {
        this.todos = todos;
    }

    public List<Todo> getTodos() {
        return todos;
    }

    public Todo addTodo(Todo todo){
        boolean add = this.todos.add(todo);
        if (!add){
            throw new NoSuchElementException("Adding the "+todo.toString()+" failed.");
        }else {
            return todo;
        }
    }

    public Todo getById(String id){
        for (Todo s :
                this.todos) {
            if (s.id().equals(id)) {
                return s;
            }
        }
        throw new ToDoNotFoundException("Das Todo mit der ID["+id+"] wurde nicht gefunden");
    }

    public Todo updateTodo(String id , Todo editedToDo){
        int indexOfCurrent = this.todos.indexOf(getById(id));
        this.todos.set(indexOfCurrent,editedToDo);
        return editedToDo;
    }

    public String delete(String id) {
        String oldId = getById(id).id();
        boolean remove = this.todos.remove(getById(id));
        if (!remove){
            throw new NoSuchElementException();
        }
        return oldId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TodoRepo todoRepo = (TodoRepo) o;
        return Objects.equals(todos, todoRepo.todos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(todos);
    }

    @Override
    public String toString() {
        return "TodoRepo{" +
                "todos=" + todos +
                '}';
    }
}
