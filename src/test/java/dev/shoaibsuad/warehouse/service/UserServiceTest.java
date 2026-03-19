package dev.shoaibsuad.warehouse.service;

import dev.shoaibsuad.warehouse.entity.User;
import dev.shoaibsuad.warehouse.model.UserRequest;
import dev.shoaibsuad.warehouse.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void createUser() {
        UserRequest request = new UserRequest("islam3181@gmail.com", "islam3181", "password123");

        User user = userService.createUser(request);

        User u = new User(1L, request.getEmail(), request.getUsername(), null);
        when(userRepository.save(any(User.class))).thenReturn(u);

        assertNotNull(user);
        assertNotNull(user.getId());
        assertEquals(request.getEmail(), user.getEmail());
        assertEquals(request.getUsername(), user.getUsername());
        assertEquals(request.getPassword(), user.getPassword());
    }
}