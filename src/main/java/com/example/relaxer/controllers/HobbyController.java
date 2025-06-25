package com.example.relaxer.controllers;

import com.example.relaxer.DTO.HobbyDTO;
import com.example.relaxer.services.HobbyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hobby")
@Tag(name = "HOBBY API", description = "CRUD операции для хоббисов")
public class HobbyController {
    private final HobbyService hobbyService;


    @Operation(summary = "Find hobbies", description = "Find all hobbies", responses = {
            @ApiResponse(responseCode = "200", description = "Hobbies found successfully")})
    @GetMapping
    public ResponseEntity<List<HobbyDTO>> getHobbies(){
        return ResponseEntity.ok().body(hobbyService.getAll());
    }

    @Operation(summary = "Find hobby by id", description = "Find hobby by id", responses = {
            @ApiResponse(responseCode = "200", description = "Hobby found successfully"),
            @ApiResponse(responseCode = "404", description = "Hobby not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<HobbyDTO> getHobbyById(@PathVariable Long id){
        return ResponseEntity.ok().body(hobbyService.findById(id));
    }

    @Operation(summary = "Create hobby", description = "Create hobby", responses = {
            @ApiResponse(responseCode = "200", description = "Hobby was create successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping
    public ResponseEntity<HobbyDTO> createHobby(@RequestBody HobbyDTO hobbyDTO){
        return ResponseEntity.ok().body(hobbyService.create(hobbyDTO));
    }

    @Operation(summary = "Update hobby", description = "Update hobby", responses = {
            @ApiResponse(responseCode = "200", description = "Hobby was update successfully"),
            @ApiResponse(responseCode = "404", description = "Hobby was not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<HobbyDTO> updateHobby(@RequestBody HobbyDTO hobbyDTO, @PathVariable Long id){
        return ResponseEntity.ok().body(hobbyService.update(id, hobbyDTO));
    }

    @Operation(summary = "Delete hobby by id", description = "Delete hobby", responses = {
            @ApiResponse(responseCode = "200", description = "Hobby was delete successfully"),
            @ApiResponse(responseCode = "404", description = "Hobby not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<HobbyDTO> deleteHobby(@PathVariable Long id){
        hobbyService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}