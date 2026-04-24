package com.example.urlshortener.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="links")
public class Link {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;

    @Column(name= "short_url",nullable=false)
    String shortUrl;

    @Column(name= "original_url",nullable=false)
    String originalUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name= "user_id",nullable=false)
    User user;
}
