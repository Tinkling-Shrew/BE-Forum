package com.tinklingshrew.forum_be.services;

import com.tinklingshrew.forum_be.dtos.CommentDTO;
import com.tinklingshrew.forum_be.entities.Comment;
import com.tinklingshrew.forum_be.entities.Post;
import com.tinklingshrew.forum_be.entities.User;
import com.tinklingshrew.forum_be.exceptions.CustomException;
import com.tinklingshrew.forum_be.mappers.CommentMapper;
import com.tinklingshrew.forum_be.repositories.CommentRepository;
import com.tinklingshrew.forum_be.repositories.PostRepository;
import com.tinklingshrew.forum_be.repositories.UserRepository;
//import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class CommentService {
    private CommentRepository commentRepo;

    private PostRepository postRepo;

    private UserRepository userRepo;

    @Autowired
    public CommentService(CommentRepository commentRepo,PostRepository postRepo,UserRepository userRepo) {
        this.commentRepo = commentRepo;
        this.postRepo = postRepo;
        this.userRepo = userRepo;
    }


    public Set<CommentDTO> findCommentsByPostId(Long id){
        Optional<Post> existingPost = postRepo.findById(id);
        if (existingPost.isEmpty()) {
            throw new CustomException(HttpStatus.NOT_FOUND,"User with id: "+id+" not found");
        }
        Post postEntity = existingPost.get();
        Set<Comment> commentEntities = commentRepo.findCommentsByPost(postEntity);
        return CommentMapper.toSetDto(commentEntities);
    }

    public Set<CommentDTO> findCommentsByUserId(Long id){
        Optional<User> existingUser = userRepo.findById(id);
        if (existingUser.isEmpty()) {
            throw new CustomException(HttpStatus.NOT_FOUND,"User with id: "+id+" not found");
        }
        User userEntity = existingUser.get();
        Set<Comment> commentEntities = commentRepo.findCommentsByUser(userEntity);
        return CommentMapper.toSetDto(commentEntities);
    }

    public Set<CommentDTO> findCommentsByParentId(Long id){
        Optional<Comment> existingComment = commentRepo.findById(id);
        if (existingComment.isEmpty()) {
            throw new CustomException(HttpStatus.NOT_FOUND,"Comment with id: "+id+" not found");
        }
        Comment commentEntity = existingComment.get();
        Set<Comment> commentEntities = commentRepo.findCommentsByParent(commentEntity);
        return CommentMapper.toSetDto(commentEntities);
    }

    public CommentDTO addComment(CommentDTO answerDto){
        Comment answer = CommentMapper.toEntity(answerDto);
        Comment savedComment = commentRepo.save(answer);
        return CommentMapper.toDto(savedComment);
    }

    public void deleteComment(Long id){
        Optional<Comment> existingComment = commentRepo.findById(id);
        if (existingComment.isEmpty()) {
            throw new CustomException(HttpStatus.NOT_FOUND,"User with id: "+id+" not found");
        }
        commentRepo.deleteCommentById(id);
    }

}
