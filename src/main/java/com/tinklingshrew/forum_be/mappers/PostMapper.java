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

    public static Post toEntity(PostDTO pollDto){
        Post post = new Post();

        Optional<User> existingUser = userRepo.findById(pollDto.getUserId());
        if (!existingUser.isPresent()) {
            throw new CustomException(HttpStatus.NOT_FOUND,"User with id: "+pollDto.getUserId()+" not found");
        }
        User userEntity = existingUser.get();
        post.setUser(userEntity);
        post.setContent(pollDto.getContent());
        post.setKarma(pollDto.getKarma());
        post.setTitle(pollDto.getTitle());

        //poll.setAnswers(pollDto.getAnswers());
        return post;
    }

    public static PostDTO toDto(Post poll){
        PostDTO pollDto = new PostDTO();
        pollDto.setId(poll.getId());
        pollDto.setUserId(poll.getUser().getId());
        pollDto.setContent(poll.getContent());
        pollDto.setKarma(poll.getKarma());
        pollDto.setTitle(poll.getTitle());

        //pollDto.setAnswers(poll.getAnswers());
        return pollDto;
    }

    public static Set<Post> toSetEntity(Set<PostDTO> pollDtos){
        Set<Post> polls = new HashSet<>();
        Iterator<PostDTO> it = pollDtos.iterator();
        while(it.hasNext()){
            Post poll = PostMapper.toEntity(it.next());
            polls.add(poll);
        }
        return polls;
    }

    public static Set<PostDTO> toSetDto(Set<Post> polls){
        Set<PostDTO> pollDtos = new HashSet<>();
        Iterator<Post> it = polls.iterator();
        while(it.hasNext()){
            PostDTO poll = PostMapper.toDto(it.next());
            pollDtos.add(poll);
        }
        return pollDtos;

    }
}
