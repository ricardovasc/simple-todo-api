package com.example.simple_todo_api.service;

import com.example.simple_todo_api.dto.CreateTodoDTO;
import com.example.simple_todo_api.dto.TodoDTO;
import com.example.simple_todo_api.dto.UpdateTodoDTO;
import com.example.simple_todo_api.mapper.TodoMapper;
import com.example.simple_todo_api.model.Todo;
import com.example.simple_todo_api.repository.TodoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final TodoMapper todoMapper;

    public List<TodoDTO> findAll() {
        return todoRepository.findAll()
                .stream()
                .map(todoMapper::toDto)
                .collect(Collectors.toList());
    }

    public TodoDTO createTodo(CreateTodoDTO createTodoDto) {
        Todo todo = todoMapper.toEntity(createTodoDto);
        Todo savedTodo = todoRepository.save(todo);
        return todoMapper.toDto(savedTodo);
    }

    public TodoDTO updateTodo(Long id, UpdateTodoDTO updateTodoDto) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Todo not found with id: " + id));

        todoMapper.updateTodoFromDto(updateTodoDto, todo);
        Todo updatedTodo = todoRepository.save(todo);
        return todoMapper.toDto(updatedTodo);
    }

    public void deleteTodo(Long id) {
        if (!todoRepository.existsById(id)) {
            throw new EntityNotFoundException("Todo not found with id: " + id);
        }
        todoRepository.deleteById(id);
    }
}
