package com.company.dms.user;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class UserDto {
    private UUID id;
    private String password;
    private String email;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}