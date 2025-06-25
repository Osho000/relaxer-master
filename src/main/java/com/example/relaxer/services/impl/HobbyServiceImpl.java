package com.example.relaxer.services.impl;

import com.example.relaxer.DTO.HobbyDTO;
import com.example.relaxer.entity.Hobby;
import com.example.relaxer.repositories.HobbyRepository;
import com.example.relaxer.services.HobbyService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class HobbyServiceImpl implements HobbyService {
    private final HobbyRepository hobbyRepository;

    @Override
    public HobbyDTO create(HobbyDTO hobbyDTO) {
        return new HobbyDTO(hobbyRepository.save(Hobby.builder().type(hobbyDTO.type()).build()).getType());
    }

    @Override
    public HobbyDTO findById(Long id) {
        return HobbyDTO.builder().type(hobbyRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Hobby not found")).getType()).build();
    }

    @Override
    public HobbyDTO update(Long id, HobbyDTO hobbyDTO) {
        hobbyRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Hobby not found")).setType(hobbyDTO.type());
        return hobbyDTO;
    }

    @Override
    public void deleteById(Long id) {
        hobbyRepository.deleteById(id);
    }

    @Override
    public List<HobbyDTO> getAll() {
        return hobbyRepository.findAll().stream().map(hobby -> HobbyDTO.builder().type(hobby.getType()).build()).collect(Collectors.toList());
    }
}
