package com.company.dms.user.repository;

import com.company.dms.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    List<UserEntity> findByUsernameContainingIgnoreCase(String username);
    List<UserEntity> findByEmailContainingIgnoreCase(String email);
    List<UserEntity> findByFirstNameContainingIgnoreCase(String firstName);
    List<UserEntity> findByLastNameContainingIgnoreCase(String lastName);
    List<UserEntity> findByPhoneNumberContaining(String phoneNumber);
    List<UserEntity> findByIsActive(Boolean isActive);
}