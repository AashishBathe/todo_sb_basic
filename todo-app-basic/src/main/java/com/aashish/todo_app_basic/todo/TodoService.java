package com.aashish.todo_app_basic.todo;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class TodoService {

    private static List<Todo> todos = new ArrayList<>();
    private static int todosCount = 0;
    static {
        todos.add(new Todo(++todosCount, "aash", "Learn SB", LocalDate.now().plusDays(5), false));
        todos.add(new Todo(++todosCount, "aash", "Learn AWS", LocalDate.now().plusDays(7), false));
        todos.add(new Todo(++todosCount, "aash", "Learn JAVA", LocalDate.now().plusDays(69), false));
    }

    public List<Todo> findByUsername (String username) {
        Predicate<? super Todo> predicate = todo -> todo.getUsername().equalsIgnoreCase(username);
        return todos.stream().filter(predicate).toList();
    }

    public void addTodo (String username, String description, LocalDate target, boolean done) {
        Todo todo = new Todo(++todosCount, username, description, target, done);
        todos.add(todo);
    }

    public void deleteById(int id) {
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        todos.removeIf(predicate);
    }

    public void updateTodo(@Valid Todo todo) {
        deleteById(todo.getId());
        todos.add(todo);

    }

    public Todo findById(int id) {
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        return todos.stream().filter(predicate).findFirst().get();
    }
}
