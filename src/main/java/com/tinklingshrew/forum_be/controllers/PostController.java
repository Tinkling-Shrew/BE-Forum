package com.tinklingshrew.forum_be.controllers;

import com.tinklingshrew.forum_be.dtos.PostDTO;
import com.tinklingshrew.forum_be.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;
    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("")
    public ResponseEntity<Set<PostDTO>> getAllPosts () {
        Set<PostDTO> postDTOS = postService.findAllPosts();
        return new ResponseEntity<>(postDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById (@PathVariable("id") Long id) {
        PostDTO postDTO = postService.findPostById(id);
        return new ResponseEntity<>(postDTO, HttpStatus.OK);
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<Set<PostDTO>> getPostsByUserId (@PathVariable("id") Long id) {
        Set<PostDTO> postDTOS = postService.findPostsByUserId(id);
        return new ResponseEntity<>(postDTOS, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<PostDTO> addPost(@RequestBody PostDTO postDTO) {
        PostDTO newPost = postService.addPost(postDTO);
        return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO) {
        PostDTO updatedPost = postService.updatePost(postDTO);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable("id") Long id) {
        postService.deletePost(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
