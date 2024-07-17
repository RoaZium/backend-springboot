package com.company.dms.user;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserDto {
    private String id;
    private String password;
    private String email;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}