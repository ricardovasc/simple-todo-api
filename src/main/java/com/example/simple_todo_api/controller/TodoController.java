package com.example.simple_todo_api.controller;

import com.example.simple_todo_api.dto.CreateTodoDTO;
import com.example.simple_todo_api.dto.TodoDTO;
import com.example.simple_todo_api.dto.UpdateTodoDTO;
import com.example.simple_todo_api.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping
    public ResponseEntity<List<TodoDTO>> getAllTodos() {
        return ResponseEntity.ok(todoService.findAll());
    }

    @PostMapping
    public ResponseEntity<TodoDTO> createTodo(@RequestBody CreateTodoDTO createTodoDTO) {
        TodoDTO createdTodo = todoService.createTodo(createTodoDTO);
        return new ResponseEntity<>(createdTodo, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TodoDTO> updateTodo(@PathVariable Long id, @RequestBody UpdateTodoDTO updateTodoDTO) {
        return ResponseEntity.ok(todoService.updateTodo(id, updateTodoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }
}
