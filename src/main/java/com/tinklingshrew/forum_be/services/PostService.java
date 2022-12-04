package com.tinklingshrew.forum_be.services;

import com.tinklingshrew.forum_be.dtos.PostDTO;
import com.tinklingshrew.forum_be.entities.Post;
import com.tinklingshrew.forum_be.entities.User;
import com.tinklingshrew.forum_be.exceptions.CustomException;
import com.tinklingshrew.forum_be.mappers.PostMapper;
import com.tinklingshrew.forum_be.repositories.PostRepository;
import com.tinklingshrew.forum_be.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class PostService {
    private PostRepository PostRepo;
    private UserRepository userRepo;

    @Autowired
    public PostService( PostRepository PostRepo,UserRepository userRepo) {
        this.PostRepo = PostRepo;
        this.userRepo = userRepo;
    }


    public PostDTO addPost(PostDTO PostDto){
        Post Post = PostMapper.toEntity(PostDto);
        Post savedPost = PostRepo.save(Post);
        return PostMapper.toDto(savedPost);
    }
    public Set<PostDTO> findAllPosts(){
        Set<Post> Posts = new HashSet<>(PostRepo.findAll());
        return PostMapper.toSetDto(Posts);
    }
    public PostDTO updatePost(PostDTO PostDto){
        Post updatedEntityPost = PostMapper.toEntity(PostDto);
        updatedEntityPost = PostRepo.save(updatedEntityPost);
        return PostMapper.toDto(updatedEntityPost);
    }

    public PostDTO findPostById(Long PostId){
        Optional<Post> existingPost = PostRepo.findById(PostId);
        if (existingPost.isEmpty()) {
            throw new CustomException(HttpStatus.NOT_FOUND,"User with id: "+PostId+" not found");
        }
        Post PostEntity = existingPost.get();
        return PostMapper.toDto(PostEntity);
    }

    public Set<PostDTO> findPostsByUserId(Long userId){
        Optional<User> existingUser = userRepo.findById(userId);
        if (existingUser.isEmpty()) {
            throw new CustomException(HttpStatus.NOT_FOUND,"User with id: "+userId+" not found");
        }
        User userEntity = existingUser.get();
        Set<Post> PostEntities = PostRepo.findPostsByUser(userEntity);
        return PostMapper.toSetDto(PostEntities);
    }

    public void deletePost(Long PostId){
        Optional<Post> existingPost = PostRepo.findById(PostId);
        if (existingPost.isEmpty()) {
            throw new CustomException(HttpStatus.NOT_FOUND,"User with id: "+PostId+" not found");
        }
        PostRepo.deletePostById(PostId);
    }

}
