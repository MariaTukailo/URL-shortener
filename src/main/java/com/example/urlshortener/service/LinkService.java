package com.example.urlshortener.service;

import com.example.urlshortener.entity.Link;
import com.example.urlshortener.entity.User;
import com.example.urlshortener.repository.LinkRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class LinkService {
    private final LinkRepository linkRepository;


    public Link findById(Long id){
        return linkRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"link was not found with id: "+id));
    }

     public List<Link> findAll(){
        return linkRepository.findAll();
     }

    public void deleteLink(Long id){
        linkRepository.deleteById(id);
        log.info("Link deleted: id={}", id);

    }

    public Link findByShortUrl(String shortUrl){
        return linkRepository.findByShortUrl(shortUrl)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"short URL was not found :"+shortUrl));
    }

    public Link createLink (String originalUrl, User user){
        System.out.println(">>> LinkService.createLink CALLED: " + originalUrl);
        Link link =new Link();
        link.setOriginalUrl(originalUrl);
        link.setUser(user);
        link.setShortUrl(generateShortCode());
        log.info("Link created: {} -> {}", link.getShortUrl(), originalUrl);
        return linkRepository.save(link);
    }

    private String generateShortCode() {
        String shortCode;
        int attempts = 0;
        do {
            shortCode = RandomStringUtils.randomAlphanumeric(6);
            attempts++;
            if (attempts > 100) {
                throw new RuntimeException("Failed to generate unique short code");
            }
        } while (linkRepository.existsByShortUrl(shortCode));
        return shortCode;
    }

    public List<Link> findByUser(Long userId){

        return linkRepository.findByUserId(userId);

    }

}
