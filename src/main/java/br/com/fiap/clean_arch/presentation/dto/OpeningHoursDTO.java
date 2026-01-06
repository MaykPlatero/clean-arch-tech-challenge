package br.com.fiap.clean_arch.presentation.dto;

import jakarta.validation.constraints.NotBlank;

public record OpeningHoursDTO(
    @NotBlank(message = "Day of week is required")
    String dayOfWeek,

    @NotBlank(message = "Open time is required")
    String openTime,

    @NotBlank(message = "Close time is required")
    String closeTime
) {}

