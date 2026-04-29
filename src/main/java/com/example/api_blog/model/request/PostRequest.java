package com.example.api_blog.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PostRequest {
    private String title;
    private String description;
    // FIXED: removed userId — will be extracted from JWT in service layer
}