package com.example.relaxer.services.impl;

import com.example.relaxer.DTO.PassportDTO;
import com.example.relaxer.entity.Passport;
import com.example.relaxer.repositories.PassportRepository;
import com.example.relaxer.services.PassportService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class PassportServiceImpl implements PassportService {
    private final PassportRepository passportRepository;

    @Override
    public PassportDTO create(PassportDTO passportDTO) {
        return PassportDTO.builder().number(passportRepository.save(Passport.builder().number(passportDTO.number()).build()).getNumber()).build();
    }

    @Override
    public PassportDTO update(Long id, PassportDTO passportDTO) {
        Passport currentPassport = passportRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Passport not found"));
        currentPassport.setNumber(passportDTO.number());
        passportRepository.save(currentPassport);
        return passportDTO;
    }

    @Override
    public PassportDTO findById(Long id) {
        return PassportDTO.builder().number(passportRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Passport not found")).getNumber()).build();
    }

    @Override
    public void deleteById(Long id) {
        passportRepository.deleteById(id);
    }

    @Override
    public List<PassportDTO> getAll() {
        return passportRepository.findAll().stream().map(pass -> PassportDTO.builder().number(pass.getNumber()).build()).toList();
    }
}
