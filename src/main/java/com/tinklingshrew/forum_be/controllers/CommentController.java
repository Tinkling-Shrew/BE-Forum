package com.tinklingshrew.forum_be.controllers;

import com.tinklingshrew.forum_be.dtos.CommentDTO;
import com.tinklingshrew.forum_be.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<Set<CommentDTO>> getCommentsByPostId (@PathVariable("id") Long id) {
        Set<CommentDTO> answers = commentService.findCommentsByPostId(id);
        return new ResponseEntity<>(answers, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Set<CommentDTO>> getCommentssByUserId (@PathVariable("id") Long id) {
        Set<CommentDTO> comments = commentService.findCommentsByUserId(id);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<CommentDTO> addComment(@RequestBody CommentDTO commentDTO) {
        CommentDTO newComment = commentService.addComment(commentDTO);
        return new ResponseEntity<>(newComment, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable("id") Long id) {
        commentService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
