package com.company.dms.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class UserDto {

    @NotNull(message = "User ID is required")
    private UUID id;

    @NotBlank(message = "UserName is required")
    private String username;

    @NotBlank(message = "Email is required")
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String passwordHash;

    @NotBlank(message = "FirstName is required")
    private String firstName;

    @NotBlank(message = "LastName is required")
    private String lastName;

    private String phoneNumber;

    private LocalDate birthDate;

    private boolean isActive;

    private LocalDateTime lastLoginAt;

    @NotNull(message = "CreatedAt is required")
    private LocalDateTime createdAt;

    @NotNull(message = "UpdatedAt is required")
    private LocalDateTime updatedAt;
}