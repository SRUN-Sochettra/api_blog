package com.example.api_blog.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// FIXED: safe user representation — never expose password
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponse {
    private long userId;
    private String userName;
    private String email;
}
