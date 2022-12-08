package com.tinklingshrew.forum_be.services;

import com.tinklingshrew.forum_be.dtos.UserDTO;
import com.tinklingshrew.forum_be.entities.User;
import com.tinklingshrew.forum_be.exceptions.CustomException;
import com.tinklingshrew.forum_be.mappers.UserMapper;
import com.tinklingshrew.forum_be.repositories.UserRepository;
//import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UserService {
    private UserRepository userRepository;


    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public UserDTO addUser(UserDTO userDto){
        User user = UserMapper.toEntity(userDto);
        User savedUser = userRepository.save(user);
        UserDTO newUserDto = UserMapper.toDto(savedUser);
        return  newUserDto;
    }
    public Set<UserDTO> findAllUsers(){
        Set<User> users = new HashSet<User>(userRepository.findAll());
        return UserMapper.toSetDto(users);
    }

    public UserDTO findUserById(Long userId){
        Optional<User> existingUser = userRepository.findById(userId);
        if (!existingUser.isPresent()) {
            throw new CustomException(HttpStatus.NOT_FOUND,"User with id: "+userId+" not found");
        }
        User userEntity = existingUser.get();
        UserDTO updatedUserDto = UserMapper.toDto(userEntity);
        return  updatedUserDto;
    }

    public void deleteUser(Long userId){
        Optional<User> existingUser = userRepository.findById(userId);
        if (!existingUser.isPresent()) {
            throw new CustomException(HttpStatus.NOT_FOUND,"User with id: "+userId+" not found");
        }
        userRepository.deleteUserById(userId);
    }
}
