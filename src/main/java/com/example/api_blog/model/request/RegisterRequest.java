package com.example.api_blog.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class RegisterRequest {
    private String userName;
    private String email;
    private String password;
    // FIXED: removed createdAt — server should set this, not client
}