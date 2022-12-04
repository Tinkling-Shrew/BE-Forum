package com.tinklingshrew.forum_be.mappers;

import com.tinklingshrew.forum_be.dtos.UserDTO;
import com.tinklingshrew.forum_be.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Configuration
public class UserMapper {

    @Autowired
    public UserMapper() {
    }

    public static User toEntity(UserDTO userDto){
        User user = new User();

        user.setDescription(userDto.getDescription());
        user.setRole(userDto.getRole());
        user.setHeader_url(userDto.getHeader_url());
        user.setPfp_url(userDto.getPfp_url());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setDate_of_creation(userDto.getDate_of_creation());
        user.setKarma(userDto.getKarma());

        return user;
    }

    public static UserDTO toDto(User user){
        UserDTO userDto = new UserDTO();
        userDto.setId(user.getId());
        userDto.setDescription(user.getDescription());
        userDto.setRole(user.getRole());
        userDto.setHeader_url(user.getHeader_url());
        userDto.setPfp_url(user.getPfp_url());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setDate_of_creation(user.getDate_of_creation());
        userDto.setKarma(user.getKarma());

        return userDto;
    }

    public static Set<User> toSetEntity(Set<UserDTO> userDtos){
        Set<User> users = new HashSet<>();
        Iterator<UserDTO> it = userDtos.iterator();
        while(it.hasNext()){
            User user = UserMapper.toEntity(it.next());
            users.add(user);
        }
        return users;
    }

    public static Set<UserDTO> toSetDto(Set<User> users){
        Set<UserDTO> userDtos = new HashSet<>();
        Iterator<User> it = users.iterator();
        while(it.hasNext()){
            UserDTO user = UserMapper.toDto(it.next());
            userDtos.add(user);
        }
        return userDtos;

    }
}
