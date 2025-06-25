package com.example.relaxer.services;

import com.example.relaxer.DTO.HobbyDTO;

import java.util.List;

public interface HobbyService {
    HobbyDTO create(HobbyDTO hobbyDTO);
    HobbyDTO update(Long id, HobbyDTO hobbyDTO);
    void deleteById(Long id);
    List<HobbyDTO> getAll();
    HobbyDTO findById(Long id);
}
