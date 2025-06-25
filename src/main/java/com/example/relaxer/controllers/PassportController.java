package com.example.relaxer.controllers;

import com.example.relaxer.DTO.PassportDTO;
import com.example.relaxer.services.PassportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/passport")
@Tag(name = "PASSPORT API", description = "CRUD операции для паспорта")
public class PassportController {
    private final PassportService passportService;

    @Operation(summary = "Get passports", description = "Create new passport for users", responses = {
            @ApiResponse(responseCode = "200", description = "Passport found successfully")})
    @GetMapping
    public ResponseEntity<List<PassportDTO>> getPassports() {
        return ResponseEntity.ok().body(passportService.getAll());
    }

    @Operation(summary = "Find passport by id", description = "Find passport by id for users", responses = {
            @ApiResponse(responseCode = "200", description = "Passport was found successfully"),
            @ApiResponse(responseCode = "400", description = "Passport not found")})
    @GetMapping("/{id}")
    public ResponseEntity<PassportDTO> getPassportById(@PathVariable Long id) {
        return ResponseEntity.ok().body(passportService.findById(id));
    }

    @Operation(summary = "Create passport", description = "Create new passport for users", responses = {
            @ApiResponse(responseCode = "200", description = "Passport was created successfully")
    })
    @PostMapping
    public ResponseEntity<PassportDTO> createPassport(@RequestBody PassportDTO passportDTO) {
        return ResponseEntity.ok().body(passportService.create(passportDTO));
    }

    @Operation(summary = "Update passport", description = "Update passport by id", responses = {
            @ApiResponse(responseCode = "200", description = "Passport was update successfully"),
            @ApiResponse(responseCode = "404", description = "Passport not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<PassportDTO> updatePassport(@RequestBody PassportDTO passportDTO, @PathVariable Long id) {
        return ResponseEntity.ok().body(passportService.update(id, passportDTO));
    }


    @Operation(summary = "Delete passport", description = "Delete passport by id", responses = {
            @ApiResponse(responseCode = "200", description = "Passport was delete successfully"),
            @ApiResponse(responseCode = "404", description = "Passport not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<PassportDTO> deletePassport(@PathVariable Long id) {
        passportService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
