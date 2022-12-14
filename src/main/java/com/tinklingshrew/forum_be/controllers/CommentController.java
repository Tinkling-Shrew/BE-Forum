package com.tinklingshrew.forum_be.controllers;

import com.tinklingshrew.forum_be.dtos.CommentDTO;
import com.tinklingshrew.forum_be.dtos.PostDTO;
import com.tinklingshrew.forum_be.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comments")
@CrossOrigin(origins = "http://localhost:3000")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<TreeSet<CommentDTO>> getCommentsByPostId (@PathVariable("id") Long id) {
        TreeSet<CommentDTO> comments = new TreeSet<>(commentService.findCommentsByPostId(id).stream().collect(Collectors.toList()));
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<TreeSet<CommentDTO>> getCommentsByUserId (@PathVariable("id") Long id) {
        TreeSet<CommentDTO> comments = new TreeSet<>(commentService.findCommentsByUserId(id).stream().collect(Collectors.toList()));
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/comment/{id}")
    public ResponseEntity<TreeSet<CommentDTO>> getCommentsByCommentId (@PathVariable("id") Long id) {
        TreeSet<CommentDTO> comments = new TreeSet<>(commentService.findCommentsByParentId(id).stream().collect(Collectors.toList()));
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
