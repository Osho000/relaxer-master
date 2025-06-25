package com.example.relaxer.services;

import com.example.relaxer.DTO.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO create(UserDTO userDTO);
    UserDTO findById(Long id);
    List<UserDTO> getAll();
    UserDTO update(Long id, UserDTO user);
    void deleteById(Long id);
}
