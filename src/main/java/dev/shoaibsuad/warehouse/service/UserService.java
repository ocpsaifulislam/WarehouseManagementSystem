package dev.shoaibsuad.warehouse.service;

import dev.shoaibsuad.warehouse.entity.User;
import dev.shoaibsuad.warehouse.model.UserRequest;
import dev.shoaibsuad.warehouse.model.UserResponse;
import dev.shoaibsuad.warehouse.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResponse createUser(UserRequest request) {
        User entity = new User();
        entity.setUsername(request.getUsername());
        entity.setEmail(request.getEmail());
        User savedUser = userRepository.save(entity);
        UserResponse response = new UserResponse();
        response.setId(savedUser.getId());
        response.setUsername(savedUser.getUsername());
        response.setEmail(savedUser.getEmail());
        return response;

    }

    public UserResponse getUserById(Long id) {
        return userRepository.findById(id)  .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }
}
