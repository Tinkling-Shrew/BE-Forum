package com.tinklingshrew.forum_be.mappers;

import com.tinklingshrew.forum_be.dtos.CommentDTO;
import com.tinklingshrew.forum_be.entities.Comment;
import com.tinklingshrew.forum_be.entities.Post;
import com.tinklingshrew.forum_be.entities.User;
import com.tinklingshrew.forum_be.exceptions.CustomException;
import com.tinklingshrew.forum_be.repositories.CommentRepository;
import com.tinklingshrew.forum_be.repositories.PostRepository;
import com.tinklingshrew.forum_be.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

@Configuration
public class CommentMapper {
    private static UserRepository userRepo;
    private static PostRepository postRepo;
    private static CommentRepository commentRepo;

    @Autowired
    public CommentMapper(UserRepository userRepo,PostRepository postRepo,CommentRepository commentRepo) {
        this.userRepo = userRepo;
        this.postRepo = postRepo;
        this.commentRepo = commentRepo;
    }

    public static Comment toEntity(CommentDTO commentDto){
        Comment comment = new Comment();

        Optional<User> existingUser = userRepo.findById(commentDto.getUserId());
        if (existingUser.isEmpty()) {
            throw new CustomException(HttpStatus.NOT_FOUND,"User with id: "+commentDto.getUserId()+" not found");
        }
        User userEntity = existingUser.get();

        Optional<Post> existingPost = postRepo.findById(commentDto.getPostId());
        if (existingPost.isEmpty()) {
            throw new CustomException(HttpStatus.NOT_FOUND,"Post with id: "+commentDto.getPostId()+" not found");
        }
        Post postEntity = existingPost.get();

        // Try to find a parent comment. If there's none, set the parent to null.
        Optional<Comment> parentComment = commentRepo.findById(commentDto.getParentId());
        Comment parentCommentEntity = null;
        if (parentComment.isPresent())
            parentCommentEntity = parentComment.get();

        comment.setId(commentDto.getId());
        comment.setContent(commentDto.getContent());
        comment.setParent(parentCommentEntity);
        comment.setUser(userEntity);
        comment.setPost(postEntity);
        return comment;
    }

    public static CommentDTO toDto(Comment comment){
        CommentDTO commentDto = new CommentDTO();
        commentDto.setId(comment.getId());
        commentDto.setContent(comment.getContent());
        if (comment.getParent() != null)
            commentDto.setParentId(comment.getParent().getId());
        else
            commentDto.setParentId(null);
        commentDto.setUserId(comment.getUser().getId());
        commentDto.setPostId(comment.getPost().getId());
        return commentDto;
    }

    public static Set<Comment> toSetEntity(Set<CommentDTO> commentDtos){
        Set<Comment> comments = new HashSet<>();
        for (CommentDTO commentDto : commentDtos) {
            Comment answer = CommentMapper.toEntity(commentDto);
            comments.add(answer);
        }
        return comments;
    }

    public static Set<CommentDTO> toSetDto(Set<Comment> comment){
        Set<CommentDTO> commentDtos = new HashSet<>();
        for (Comment value : comment) {
            CommentDTO commentDto = CommentMapper.toDto(value);
            commentDtos.add(commentDto);
        }
        return commentDtos;

    }
}
