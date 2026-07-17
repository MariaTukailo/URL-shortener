package com.example.urlshortener;

import com.example.urlshortener.entity.Link;
import com.example.urlshortener.entity.User;
import com.example.urlshortener.repository.LinkRepository;
import com.example.urlshortener.service.LinkService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class LinkServiceTest {

    @Mock
    private LinkRepository linkRepository;

    @InjectMocks
    private LinkService linkService;

    @Test
    void shouldFindById() {
    Link link=new Link();
    link.setId(1L);

    when(linkRepository.findById(1L)).thenReturn(Optional.of(link));
    Link found=linkService.findById(1L);

  assertEquals(1L, found.getId());
   verify(linkRepository).findById(1L);

    }

    @Test
    void shouldThrowWhenLinkNotFoundById() {
    when(linkRepository.findById(99L)).thenReturn(Optional.empty());
    assertThrows(ResponseStatusException.class,()->{
        linkService.findById(99L);
        });

    }

    @Test
    void shouldFindAll(){
        Link link1=new Link();
        link1.setId(1L);
        Link link2=new Link();
        link2.setId(2L);

        when(linkRepository.findAll()).thenReturn(List.of(link1,link2));
        List<Link> found=linkService.findAll();

        assertEquals(2,found.size());
        verify(linkRepository).findAll();
    }

    @Test
    void shouldDeleteLinkById(){
        linkService.deleteLink(1L);
        verify(linkRepository).deleteById(1L);

    }

    @Test
    void shouldFindByUser() {
        Link link=new Link();
        link.setId(1L);


        when(linkRepository.findByUserId(1L)).thenReturn(List.of(link));
        List<Link> found=linkService.findByUser(1L);

        assertEquals(1, found.size());
        verify(linkRepository).findByUserId(1L);

    }

    @Test
    void shouldFindByShortUrl() {
        Link link=new Link();
        link.setId(1L);
        link.setShortUrl("123456");


        when(linkRepository.findByShortUrl("123456")).thenReturn(Optional.of(link));
        Link found=linkService.findByShortUrl("123456");

        assertEquals("123456",found.getShortUrl());
        verify(linkRepository).findByShortUrl("123456");

    }

    @Test
    void shouldThrowWhenNotFindByShortUrl() {
        when(linkRepository.findByShortUrl("123not")).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class,()->{
            linkService.findByShortUrl("123not");
        });
    }

    @Test
    void shouldCreateLink() {
        User user = new User();
        user.setId(1L);
        user.setUsername("alex");

        when(linkRepository.existsByShortUrl(anyString())).thenReturn(false);
        when(linkRepository.save(any(Link.class))).thenAnswer(inv -> inv.getArgument(0));

        Link link = linkService.createLink("https://ozon.ru", user);

        assertNotNull(link);
        assertEquals("https://ozon.ru", link.getOriginalUrl());
        assertEquals(user, link.getUser());
        assertNotNull(link.getShortUrl());
        verify(linkRepository).save(any(Link.class));
    }



}
