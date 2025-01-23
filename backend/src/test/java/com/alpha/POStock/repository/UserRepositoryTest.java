package com.alpha.POStock.repository;

import com.alpha.POStock.entity.User;
import com.alpha.POStock.entity.enums.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void setUpUser(){
        user = new User();
        user.setName("test");
        user.setEmail("test@gmail.com");
        user.setPassword("hola123");
        user.setRole(Role.ADMINISTRADOR);
    }

    @Test
    void testCreateUser(){
        User createdUser = userRepository.save(user);

        assertThat(createdUser).isNotNull();
        assertThat(createdUser.getId()).isNotNull();
        assertThat(createdUser.getEmail()).isEqualTo("test@gmail.com");
    }

    @Test
    void testUpdateUser(){
        User createdUser = userRepository.save(user);

        createdUser.setName("test update");
        createdUser.setEmail("testUpdate@gmail.com");

        User updatedUser = userRepository.saveAndFlush(createdUser);

        assertThat(updatedUser.getName()).isEqualTo("test update");
        assertThat(updatedUser.getEmail()).isEqualTo("testUpdate@gmail.com");
    }

    @Test
    void testDeleteUser(){
        User createdUser = userRepository.save(user);

        userRepository.delete(createdUser);

        Optional<User> deletedUser = userRepository.findById(createdUser.getId());
        assertThat(deletedUser).isNotPresent();
    }

    @Test
    void testFindUserById(){
        User createdUser = userRepository.save(user);
        Optional<User> findUser = userRepository.findById(createdUser.getId());

        assertThat(findUser).isPresent();
        assertThat(findUser.get().getName()).isEqualTo("test");
    }

    @Test
    void testUFindUserByEmail(){
        userRepository.save(user);

        Optional<User> userOptional = userRepository.findByEmail("test@gmail.com");

        assertThat(userOptional).isPresent();
        assertThat(userOptional.get().getEmail()).isEqualTo("test@gmail.com");
    }

}
