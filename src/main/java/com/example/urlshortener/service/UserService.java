package com.example.urlshortener.service;

import com.example.urlshortener.entity.User;
import com.example.urlshortener.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

public User findById(Long id) {
    return userRepository.findById(id)
            .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"User was not found with id:"+id));
    }

    public List<User> findAll(){
    return  userRepository.findAll();
    }

    public User findByUsername(String username){
    return userRepository.findByUsername(username)
            .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"User was not found with username:" + username));
    }
    public void deleteUserById(Long id){

    userRepository.deleteById(id);
    log.info("User deleted: id={}", id);
    }

    public User updateUser(Long id,String email,String username){
    User user=userRepository.findById(id)
            .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"User was not found with id:"+id));
        Optional.ofNullable(email).ifPresent(user::setEmail);
        Optional.ofNullable(username).ifPresent(user::setUsername);
        return userRepository.save(user);

    }


}
