package com.blogApplication.Blog.Application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter
    private int id;

    @Column(name="user_name",nullable=false, length=100)
    private String name;
    private String email;
    private String password;
    private String about;


}
