package dev.shoaibsuad.warehouse.controller;

import dev.shoaibsuad.warehouse.entity.User;
import dev.shoaibsuad.warehouse.model.UserRequest;
import dev.shoaibsuad.warehouse.model.UserResponse;
import dev.shoaibsuad.warehouse.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public UserResponse createUser(UserRequest request) {
        return userService.createUser(request);
    }
    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable  Long id) {
        return  userService.getUserById(id);
    }
}
