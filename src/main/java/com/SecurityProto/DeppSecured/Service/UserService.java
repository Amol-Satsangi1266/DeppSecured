package com.SecurityProto.DeppSecured.Service;

import com.SecurityProto.DeppSecured.Dto.UserRequest;
import com.SecurityProto.DeppSecured.Dto.UserResponse;
import com.SecurityProto.DeppSecured.Entity.Users;
import com.SecurityProto.DeppSecured.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Register
    public UserResponse register(UserRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        Users user = new Users();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEnabled(true);

        return mapToResponse(userRepository.save(user));
    }

    // Get All Users
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // Get User By ID
    public UserResponse getUserById(Long id) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return mapToResponse(user);
    }

    // Update User
    public UserResponse updateUser(Long id, UserRequest request) {

        Users user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getUsername().equals(request.getUsername())
                && userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return mapToResponse(userRepository.save(user));
    }

    // Delete User
    public void deleteUser(Long id) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userRepository.delete(user);
    }

    // Entity -> DTO
    private UserResponse mapToResponse(Users user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.isEnabled()
        );
    }
}