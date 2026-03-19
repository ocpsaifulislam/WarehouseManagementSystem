package dev.shoaibsuad.warehouse.entity;

import dev.shoaibsuad.warehouse.model.UserResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String email;
    private String password;

    public UserResponse toResponse() {
        UserResponse response = new UserResponse();
        response.setId(this.id);
        response.setUsername(this.username);
        response.setEmail(this.email);
        return response;
    }
}