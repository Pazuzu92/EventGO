package com.innopolis.eventgo.service.impl;

import com.innopolis.eventgo.db.entity.User;
import com.innopolis.eventgo.db.repository.*;
import com.innopolis.eventgo.exceptions.NotFoundException;
import com.innopolis.eventgo.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserServiceTest {

    private UserService userService;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository,
                mock(UserDAO.class),
                mock(RoleDAO.class),
                mock(CommentDAO.class),
                mock(GroupDAO.class),
                mock(BCryptPasswordEncoder.class));
    }

    @Test
    void updateUser() {
    }

    @Test
    void findUserDto() {
    }

    @Test
    void save() {
    }

    @Test
    void findAll() {
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }

    @Test
    void findByLogin() {
        String login = "edljkrtg";
        when(userRepository.getUser(login)).thenReturn(null);
        Assertions.assertNotNull(userService.findByLogin(login));
    }

    @Test
    void updateByRole() {
    }

    @Test
    void findUser() {
        Long id = -1L;
        when(userRepository.getUser(id)).thenReturn(null);
        Assertions.assertThrows(NotFoundException.class, () -> userService.findUser(id));
    }

    @Test
    void findAllOrderById() {
    }
}