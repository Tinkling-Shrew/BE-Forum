package com.tinklingshrew.forum_be.mappers;

import com.tinklingshrew.forum_be.dtos.PostDTO;
import com.tinklingshrew.forum_be.entities.Post;
import com.tinklingshrew.forum_be.entities.User;
import com.tinklingshrew.forum_be.exceptions.CustomException;
import com.tinklingshrew.forum_be.repositories.UserRepository;
import com.tinklingshrew.forum_be.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

public class PostMapper {
    private static UserService userService;
    private static UserRepository userRepo;

    @Autowired
    public PostMapper(UserService userService,UserRepository userRepo) {
        this.userService = userService;
        this.userRepo = userRepo;
    }

    public static Post toEntity(PostDTO postDto){
        Post post = new Post();

        Optional<User> existingUser = userRepo.findById(postDto.getUserId());
        if (!existingUser.isPresent()) {
            throw new CustomException(HttpStatus.NOT_FOUND,"User with id: "+postDto.getUserId()+" not found");
        }
        User userEntity = existingUser.get();
        post.setUser(userEntity);
        post.setContent(postDto.getContent());
        post.setKarma(postDto.getKarma());
        post.setTitle(postDto.getTitle());

        //post.setAnswers(postDto.getAnswers());
        return post;
    }

    public static PostDTO toDto(Post post){
        PostDTO postDto = new PostDTO();
        postDto.setId(post.getId());
        postDto.setUserId(post.getUser().getId());
        postDto.setContent(post.getContent());
        postDto.setKarma(post.getKarma());
        postDto.setTitle(post.getTitle());

        //postDto.setAnswers(post.getAnswers());
        return postDto;
    }

    public static Set<Post> toSetEntity(Set<PostDTO> postDtos){
        Set<Post> posts = new HashSet<>();
        Iterator<PostDTO> it = postDtos.iterator();
        while(it.hasNext()){
            Post post = PostMapper.toEntity(it.next());
            posts.add(post);
        }
        return posts;
    }

    public static Set<PostDTO> toSetDto(Set<Post> posts){
        Set<PostDTO> postDtos = new HashSet<>();
        Iterator<Post> it = posts.iterator();
        while(it.hasNext()){
            PostDTO post = PostMapper.toDto(it.next());
            postDtos.add(post);
        }
        return postDtos;

    }
}
