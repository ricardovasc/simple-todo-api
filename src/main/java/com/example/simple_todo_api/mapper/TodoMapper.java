package com.example.simple_todo_api.mapper;

import com.example.simple_todo_api.dto.CreateTodoDTO;
import com.example.simple_todo_api.dto.TodoDTO;
import com.example.simple_todo_api.dto.UpdateTodoDTO;
import com.example.simple_todo_api.model.Todo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TodoMapper {

    TodoDTO toDto(Todo todo);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "completed", constant = "false")
    Todo toEntity(CreateTodoDTO createTodoDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "description", ignore = true)
    void updateTodoFromDto(UpdateTodoDTO dto, @MappingTarget Todo entity);
}
