package com.example.relaxer.services;

import com.example.relaxer.DTO.PassportDTO;

import java.util.List;

public interface PassportService {
    PassportDTO create(PassportDTO passportDTO);
    PassportDTO update(Long id, PassportDTO passportDTO);
    PassportDTO findById(Long id);
    void deleteById(Long id);
    List<PassportDTO> getAll();
}