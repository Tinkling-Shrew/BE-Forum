package com.tinklingshrew.forum_be.controllers;

import com.google.common.hash.Hashing;
import com.tinklingshrew.forum_be.dtos.UserDTO;
import com.tinklingshrew.forum_be.dtos.UserRegisterDTO;
import com.tinklingshrew.forum_be.entities.User;
import com.tinklingshrew.forum_be.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "192.168.1.101")
public class AuthenticationController {
    private final UserService userService;

    @Autowired
    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String registerAccount(UserRegisterDTO userRegisterDTO) throws NoSuchAlgorithmException {
        System.out.println("Data: " + userRegisterDTO.getUsername());
        UserDTO existingUser = userService.findUserByUsername(userRegisterDTO.getUsername());
        if(existingUser != null)
            return "User with the username already exists.";

        String passwordHash = Hashing.sha256().hashString(userRegisterDTO.getPassword(), StandardCharsets.UTF_8).toString();

        UserDTO finalUser = userService.addUser(new UserDTO(userRegisterDTO.getUsername(), passwordHash));
        if(finalUser != null)
            return "success";
        return "error";
    }
}
