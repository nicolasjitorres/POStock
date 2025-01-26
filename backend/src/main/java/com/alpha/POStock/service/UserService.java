package com.alpha.POStock.service;

import com.alpha.POStock.entity.User;
import com.alpha.POStock.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user){
        return userRepository.save(user);
    }

    public User updateUser(Long id, User user){
        User foundUser = this.getUserById(id);
        foundUser.setName(user.getName());
        return userRepository.saveAndFlush(foundUser);
    }

    public void deleteUser(Long id){
        User foundUser = this.getUserById(id);
        userRepository.delete(foundUser);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado."));
    }

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(() -> new jakarta.persistence.EntityNotFoundException("Usuario no encontrado."));
    }

}
