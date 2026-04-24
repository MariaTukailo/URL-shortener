package com.example.urlshortener.repository;

import com.example.urlshortener.entity.Link;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository extends JpaRepository<Link,Long> {
}
