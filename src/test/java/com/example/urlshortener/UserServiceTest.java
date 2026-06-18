package com.example.urlshortener;

import com.example.urlshortener.entity.Role;
import com.example.urlshortener.entity.User;
import com.example.urlshortener.repository.UserRepository;
import com.example.urlshortener.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldFindUserByUsername(){
        User user=new User();
        user.setId(1L);
        user.setUsername("alex");
        user.setEmail("alex@mail.com");
        user.setRole(Role.USER);
        user.setPassword("hashed_password");

        when(userRepository.findByUsername("alex")).thenReturn(Optional.of(user));
        User found = userService.findByUsername("alex");

        assertEquals("alex", found.getUsername());
        assertEquals("alex@mail.com", found.getEmail());
        verify(userRepository).findByUsername("alex");

    }

    @Test
    void shouldThrowWhenUserNotFoundByUsername() {
        when(userRepository.findByUsername("no")).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> {
            userService.findByUsername("no");
        });
    }

    @Test
    void shouldFindById(){
        User user=new User();
        user.setId(1L);
        user.setUsername("alex");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        User found=userService.findById(1L);

        assertEquals(1L, found.getId());
        verify(userRepository).findById(1L);

    }

    @Test
    void shouldThrowWhenUserNotFoundById(){
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class,()->
        {userService.findById(99L);});
    }

    @Test
    void shouldFindAll(){
        User user1=new User();
        user1.setId(1L);
        user1.setUsername("alex");
        User user2=new User();
        user2.setId(2L);
        user2.setUsername("maria");

        when(userRepository.findAll()).thenReturn(List.of(user1,user2));
        List<User> found=userService.findAll();

        assertEquals(2, found.size());
        verify(userRepository).findAll();

    }

    @Test
    void shouldUpdateUser(){
        User user = new User();
        user.setId(1L);
        user.setUsername("old");
        user.setEmail("old@mail.com");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);

        User updated=userService.updateUser(1L, "new@mail.com", "new");

        assertEquals("new", updated.getUsername());
        assertEquals("new@mail.com", updated.getEmail());
        verify(userRepository).save(user);
    }

    @Test
    void shouldDeleteUserById(){
        userService.deleteUserById(1L);
        verify(userRepository).deleteById(1L);

    }



}
