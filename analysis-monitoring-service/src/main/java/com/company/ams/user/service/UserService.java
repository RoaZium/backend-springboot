package com.company.ams.user.service;

import com.company.ams.user.dto.UserDto;
import com.company.ams.user.entity.User;
import com.company.ams.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> getUsers(String username, String email, String firstName, String lastName, String phoneNumber, Boolean isActive) {
        List<User> userEntities;

        if (username != null) {
            userEntities = userRepository.findByUsernameContainingIgnoreCase(username);
        } else if (email != null) {
            userEntities = userRepository.findByEmailContainingIgnoreCase(email);
        } else if (firstName != null) {
            userEntities = userRepository.findByFirstNameContainingIgnoreCase(firstName);
        } else if (lastName != null) {
            userEntities = userRepository.findByLastNameContainingIgnoreCase(lastName);
        } else if (phoneNumber != null) {
            userEntities = userRepository.findByPhoneNumberContaining(phoneNumber);
        } else if (isActive != null) {
            userEntities = userRepository.findByIsActive(isActive);
        } else {
            userEntities = userRepository.findAll();
        }

        return userEntities.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public UserDto getUserById(UUID id) {
        return userRepository.findById(id)
                .map(this::convertToDto)
                .orElse(null);
    }

    public UserDto createUser(UserDto userDto) {
        User user = convertToEntity(userDto);
        User savedUser = userRepository.save(user);
        return convertToDto(savedUser);
    }

    public UserDto updateUser(UUID id, UserDto userDto) {
        return userRepository.findById(id)
                .map(user -> {
                    updateUserFromDto(user, userDto);
                    User updatedUser = userRepository.save(user);
                    return convertToDto(updatedUser);
                })
                .orElse(null);
    }

    public boolean deleteUser(UUID id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private UserDto convertToDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUserName(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setBirthDate(user.getBirthDate());
        dto.setActive(user.isActive());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setUpdatedAt(user.getUpdatedAt());
        return dto;
    }

    private User convertToEntity(UserDto dto) {
        User user = new User();
        user.setUsername(dto.getUserName());
        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setBirthDate(dto.getBirthDate());
        user.setActive(dto.isActive());
        return user;
    }

    private void updateUserFromDto(User user, UserDto dto) {
        user.setUsername(dto.getUserName());
        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setBirthDate(dto.getBirthDate());
        user.setActive(dto.isActive());
    }
}