package com.blogApplication.Blog.Application.services;

import com.blogApplication.Blog.Application.entity.User;
import com.blogApplication.Blog.Application.exceptions.ResourceNotFoundException;
import com.blogApplication.Blog.Application.payloads.UserDto;
import com.blogApplication.Blog.Application.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDto createUser(UserDto userDto) {
        User user= this.dtoToUser(userDto);
        User savedUser= this.userRepository.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","UserId",userId));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        User savedUser = this.userRepository.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user =this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        return this.userToDto(user);

    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users=this.userRepository.findAll();
        List<UserDto> userDtos=users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {

        User user= this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        this.userRepository.delete(user);
    }
    private User dtoToUser(UserDto userDto){
        User user=this.modelMapper.map(userDto, User.class);
        return user;
    }

    public UserDto userToDto(User user){
        UserDto userDto=this.modelMapper.map(user, UserDto.class);
        return userDto;
    }

}
