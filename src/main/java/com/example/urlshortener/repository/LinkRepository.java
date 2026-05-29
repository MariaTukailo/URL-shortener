package com.example.urlshortener.repository;

import com.example.urlshortener.entity.Link;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LinkRepository extends JpaRepository<Link,Long> {
List<Link> findByUserId(Long id);
Optional<Link> findByShortUrl(String shortUrl);
boolean existsByShortUrl(String shortUrl);
}
