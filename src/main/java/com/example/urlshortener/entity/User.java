package com.example.urlshortener.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name="role",nullable = false)
    private Role role;

    @Column(name="email",nullable = false,unique = true)
    private String email;

    @Column(name="password",nullable = false)
    private String password;

    @Column(name="username",nullable = false,length=50)
    private String username;

    @OneToMany(mappedBy = "user",cascade=CascadeType.ALL,orphanRemoval = true)
    private List<Link> links =new ArrayList<>();
}
