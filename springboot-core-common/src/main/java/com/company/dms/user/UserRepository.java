package com.company.dms.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    List<User> findByUsernameContainingIgnoreCase(String username);
    List<User> findByEmailContainingIgnoreCase(String email);
    List<User> findByFirstNameContainingIgnoreCase(String firstName);
    List<User> findByLastNameContainingIgnoreCase(String lastName);
    List<User> findByPhoneNumberContaining(String phoneNumber);
    List<User> findByIsActive(Boolean isActive);
}