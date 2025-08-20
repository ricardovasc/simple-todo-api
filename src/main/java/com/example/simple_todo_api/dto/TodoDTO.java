package com.example.simple_todo_api.dto;

import lombok.Data;

@Data
public class TodoDTO {

    private Long id;
    private String description;
    private boolean completed;
}
