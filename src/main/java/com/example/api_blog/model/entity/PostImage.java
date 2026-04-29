package com.example.api_blog.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostImage {
    private long imageId;
    private String imageUrl;
    private long postId;
}