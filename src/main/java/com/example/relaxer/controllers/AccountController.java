package com.example.relaxer.controllers;

import com.example.relaxer.DTO.AccountDTO;
import com.example.relaxer.services.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account")
@Tag(name = "ACCOUNT API", description = "CRUD операции для аккаунта")
public class AccountController {
    private final AccountService accountService;

    @Operation(summary = "Find accounts", description = "Find all accounts", responses = {
            @ApiResponse(responseCode = "200", description = "Accounts found successfully")})
    @GetMapping
    public ResponseEntity<List<AccountDTO>> getAccounts() {
        return ResponseEntity.ok().body(accountService.getAll());
    }


    @Operation(summary = "Find account by id", description = "Find account by id", responses = {
            @ApiResponse(responseCode = "200", description = "Account found successfully"),
            @ApiResponse(responseCode = "404", description = "Account not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable Long id) {
        return ResponseEntity.ok().body(accountService.findById(id));
    }

    @Operation(summary = "Create account", description = "Create account", responses = {
            @ApiResponse(responseCode = "200", description = "Account was successfully created"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping
    public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO accountDTO) {
        return ResponseEntity.ok().body(accountService.create(accountDTO));
    }

    @Operation(summary = "Update account", description = "Update account", responses = {
            @ApiResponse(responseCode = "200", description = "Account was successfully updated"),
            @ApiResponse(responseCode = "404", description = "Account was not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<AccountDTO> updateAccount(@RequestBody AccountDTO accountDTO, @PathVariable Long id) {
        return ResponseEntity.ok().body(accountService.update(id, accountDTO));
    }

    @Operation(summary = "Delete account by id", description = "Delete account", responses = {
            @ApiResponse(responseCode = "200", description = "Delete account was successfully"),
            @ApiResponse(responseCode = "404", description = "Account was not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<AccountDTO> deleteAccount(@PathVariable Long id) {
        accountService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
