package com.company.dms.user.service;

import com.company.dms.user.dto.UserDto;
import com.company.dms.user.entity.UserEntity;
import com.company.dms.user.repository.UserRepository;
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
        List<UserEntity> userEntities;

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
        UserEntity userEntity = convertToEntity(userDto);
        UserEntity savedUserEntity = userRepository.save(userEntity);
        return convertToDto(savedUserEntity);
    }

    public UserDto updateUser(UUID id, UserDto userDto) {
        return userRepository.findById(id)
                .map(userEntity -> {
                    updateUserFromDto(userEntity, userDto);
                    UserEntity updatedUserEntity = userRepository.save(userEntity);
                    return convertToDto(updatedUserEntity);
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

    private UserDto convertToDto(UserEntity userEntity) {
        UserDto dto = new UserDto();
        dto.setId(userEntity.getId());
        dto.setUsername(userEntity.getUsername());
        dto.setEmail(userEntity.getEmail());
        dto.setFirstName(userEntity.getFirstName());
        dto.setLastName(userEntity.getLastName());
        dto.setPhoneNumber(userEntity.getPhoneNumber());
        dto.setBirthDate(userEntity.getBirthDate());
        dto.setActive(userEntity.isActive());
        dto.setCreatedAt(userEntity.getCreatedAt());
        dto.setUpdatedAt(userEntity.getUpdatedAt());
        return dto;
    }

    private UserEntity convertToEntity(UserDto dto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(dto.getUsername());
        userEntity.setEmail(dto.getEmail());
        userEntity.setFirstName(dto.getFirstName());
        userEntity.setLastName(dto.getLastName());
        userEntity.setPhoneNumber(dto.getPhoneNumber());
        userEntity.setBirthDate(dto.getBirthDate());
        userEntity.setActive(dto.isActive());
        return userEntity;
    }

    private void updateUserFromDto(UserEntity userEntity, UserDto dto) {
        userEntity.setUsername(dto.getUsername());
        userEntity.setEmail(dto.getEmail());
        userEntity.setFirstName(dto.getFirstName());
        userEntity.setLastName(dto.getLastName());
        userEntity.setPhoneNumber(dto.getPhoneNumber());
        userEntity.setBirthDate(dto.getBirthDate());
        userEntity.setActive(dto.isActive());
    }
}