package com.example.relaxer.services.impl;

import com.example.relaxer.DTO.UserDTO;
import com.example.relaxer.entity.Hobby;
import com.example.relaxer.entity.User;
import com.example.relaxer.mapper.UserMapper;
import com.example.relaxer.repositories.HobbyRepository;
import com.example.relaxer.repositories.UserRepository;
import com.example.relaxer.services.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final HobbyRepository hobbyRepository;
    private final UserRepository userRepository;

    @Override
    public UserDTO create(UserDTO userDTO) {

        Set<Hobby> hobbies = userDTO.hobbies().stream()
                .map(type -> hobbyRepository.findByType(type).orElseGet(()->hobbyRepository.save(Hobby.builder().type(type).build())))
                .collect(Collectors.toSet());

        User user = UserMapper.toEntity(userDTO, hobbies.stream().toList());
        return UserMapper.toDTO(userRepository.save(user));
    }

    @Override
    public List<UserDTO> getAll() {
        return userRepository.findAll().stream().map(UserMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO findById(Long id) {
        return userRepository.findById(id).map(UserMapper::toDTO)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
    }

    @Override
    public UserDTO update(Long id, UserDTO userDTO) {

        User currentUser = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found"));
        currentUser.setName(userDTO.name());
        currentUser.setAge(userDTO.age());
        currentUser.setHobbies(userDTO.hobbies().stream().map(hobby -> Hobby.builder().type(hobby).build()).collect(Collectors.toSet()));
        return UserMapper.toDTO(userRepository.save(currentUser));
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
