package dev.shoaibsuad.warehouse.service;

import dev.shoaibsuad.warehouse.entity.User;
import dev.shoaibsuad.warehouse.model.UserRequest;
import dev.shoaibsuad.warehouse.model.UserResponse;
import dev.shoaibsuad.warehouse.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User createUser(UserRequest request) {
        User entity = new User();
//        entity.setPassword(request.getPassword());
        entity.setUsername(request.getUsername());
        entity.setEmail(request.getEmail());
        return userRepository.save(entity);
    }

    public UserResponse getUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        return response;
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> {
                    UserResponse response = new UserResponse();
                    response.setId(user.getId());
                    response.setUsername(user.getUsername());
                    response.setEmail(user.getEmail());
                    return response;
                })
                .toList();
    }
}
