package com.example.relaxer.services;

import com.example.relaxer.DTO.AccountDTO;

import java.util.List;

public interface AccountService {
    AccountDTO create(AccountDTO accountDTO);
    AccountDTO update(Long id, AccountDTO accountDTO);
    List<AccountDTO> getAll();
    AccountDTO findById(Long id);
    void deleteById(Long id);
}
