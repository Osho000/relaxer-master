package com.example.relaxer.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record HobbyDTO(
        @NotNull
        @Size(min = 1, max = 50)
        String type
) {
}
