package com.company.dms.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class UserDto {

    @NotNull(message = "User ID is required")
    @JsonProperty("id")
    private UUID id;

    @NotBlank(message = "UserName is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    @JsonProperty("userName")
    private String userName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @JsonProperty("email")
    private String email;

    @JsonProperty("passwordHash")
    private String passwordHash;

    @NotBlank(message = "FirstName is required")
    @Size(max = 50, message = "First name must not exceed 50 characters")
    @JsonProperty("firstName")
    private String firstName;

    @NotBlank(message = "LastName is required")
    @Size(max = 50, message = "Last name must not exceed 50 characters")
    @JsonProperty("lastName")
    private String lastName;

    @Size(max = 20, message = "Phone number must not exceed 20 characters")
    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @JsonProperty("birthDate")
    private LocalDate birthDate;

    @JsonProperty("isActive")
    private boolean isActive;

    @JsonProperty("lastLoginAt")
    private LocalDateTime lastLoginAt;

    @NotNull(message = "CreatedAt is required")
    @JsonProperty("createdAt")
    private LocalDateTime createdAt;

    @NotNull(message = "UpdatedAt is required")
    @JsonProperty("updatedAt")
    private LocalDateTime updatedAt;
}