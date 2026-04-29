package com.example.api_blog.controller;

import com.example.api_blog.model.request.PostRequest;
import com.example.api_blog.model.response.ApiResponse;
import com.example.api_blog.model.response.PostResponse;
import com.example.api_blog.service.PostService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
@SecurityRequirement(name = "bearerAuth") // FIXED: only once, removed duplicate
public class PostController {

    private final PostService postService;

    @PostMapping(value = "/add-post", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<PostResponse>> addPost(
            @ModelAttribute PostRequest postRequest,
            @RequestPart("files") MultipartFile[] files
    ) {
        PostResponse post = postService.addPost(postRequest, files);
        return ResponseEntity.ok(
                new ApiResponse<>("Post added successfully", post, 200, LocalDateTime.now())
        );
    }
}