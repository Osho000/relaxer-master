package com.example.relaxer.controllers;

import com.example.relaxer.DTO.UserDTO;
import com.example.relaxer.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@Tag(name = "USER API", description = "CRUD операции для пользователей")
public class UserController {
    private final UserService userService;

    @Operation(summary = "Find users", description = "Find all users", responses = {
            @ApiResponse(responseCode = "200", description = "Users found successfully")
    })
    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers() {
        return ResponseEntity.ok().body(userService.getAll());
    }


    @Operation(summary = "Find user by id", description = "Find user by id", responses = {
            @ApiResponse(responseCode = "200", description = "User found successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.findById(id));
    }

    @Operation(summary = "Create user", description = "Create user", responses = {
            @ApiResponse(responseCode = "200", description = "User create successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok().body(userService.create(userDTO));
    }

    @Operation(summary = "Update user", description = "Update user", responses = {
            @ApiResponse(responseCode = "200", description = "User update successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO, @PathVariable Long id) {
        return ResponseEntity.ok().body(userService.update(id, userDTO));
    }

    @Operation(summary = "Delete user by id", description = "Delete user", responses = {
            @ApiResponse(responseCode = "200", description = "User delete successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
