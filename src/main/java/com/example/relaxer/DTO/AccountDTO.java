package com.example.relaxer.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record AccountDTO(
        @NotNull @Size(min = 2, max = 50) String title){}
