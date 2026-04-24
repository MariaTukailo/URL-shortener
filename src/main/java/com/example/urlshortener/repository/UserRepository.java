package com.example.urlshortener.repository;

import com.example.urlshortener.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
