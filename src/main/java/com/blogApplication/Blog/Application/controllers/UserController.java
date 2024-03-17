package com.blogApplication.Blog.Application.controllers;

import com.blogApplication.Blog.Application.entity.User;
import com.blogApplication.Blog.Application.payloads.UserDto;
import com.blogApplication.Blog.Application.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    //post- create user

    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        UserDto createUserDto= this.userService.createUser(userDto);
        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }

    //put- update user
    @PutMapping("/update/{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,
                                              @PathVariable Integer userId){
       UserDto updatedUser=this.userService.updateUser(userDto,userId);
       return ResponseEntity.ok(updatedUser);
    }

    //delete - delete user
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer userId){
        this.userService.deleteUser(userId);
        return new ResponseEntity(Map.of("message","User deleted Successfully"),HttpStatus.OK);
    }

    //get - user get

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Integer userId){
        UserDto fetchedUser= this.userService.getUserById(userId);
        return ResponseEntity.ok(fetchedUser);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(this.userService.getAllUsers());
    }
}
