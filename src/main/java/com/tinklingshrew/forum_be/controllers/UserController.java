package com.tinklingshrew.forum_be.controllers;

import com.tinklingshrew.forum_be.dtos.UserDTO;
import com.tinklingshrew.forum_be.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<Set<UserDTO>> getAllUsers () {
        Set<UserDTO> users = userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById (@PathVariable("id") Long id) {
        UserDTO userDto = userService.findUserById(id);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDto) {
        UserDTO newUser = userService.addUser(userDto);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
