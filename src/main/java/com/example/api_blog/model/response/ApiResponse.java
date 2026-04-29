package com.example.api_blog.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
@AllArgsConstructor
@Data
public class ApiResponse<T> {
    private String message;
    private T payload;
    private int status;
    private LocalDateTime dateTime;
}
