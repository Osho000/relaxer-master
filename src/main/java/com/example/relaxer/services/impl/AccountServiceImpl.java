package com.example.relaxer.services.impl;

import com.example.relaxer.DTO.AccountDTO;
import com.example.relaxer.entity.Account;
import com.example.relaxer.repositories.AccountRepository;
import com.example.relaxer.services.AccountService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Override
    public AccountDTO create(AccountDTO accountDTO) {
        return AccountDTO.builder().title(accountDTO.title()).build();
    }

    @Override
    public AccountDTO update(Long id, AccountDTO accountDTO) {
        Account currentAcc = accountRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Account not found"));
        currentAcc.setTitle(accountDTO.title());
        accountRepository.save(currentAcc);
        return AccountDTO.builder().title(currentAcc.getTitle()).build();
    }

    @Override
    public List<AccountDTO> getAll() {
        return accountRepository.findAll().stream().map(acc -> AccountDTO.builder().title(acc.getTitle()).build()).toList();
    }

    @Override
    public AccountDTO findById(Long id) {
        return AccountDTO.builder().title(accountRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Account not found")).getTitle()).build();
    }

    @Override
    public void deleteById(Long id) {
        accountRepository.deleteById(id);
    }
}
