package com.example.api_blog.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Post {
    private long postId;
    private String title;
    private String description;
    private long userId;
    private Timestamp createdAt;
}