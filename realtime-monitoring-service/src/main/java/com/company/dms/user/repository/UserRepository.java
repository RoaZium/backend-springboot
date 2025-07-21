package com.company.dms.user.repository;

import com.company.dms.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    List<User> findByUsernameIgnoreCase(String username);
    List<User> findByEmailIgnoreCase(String email);
    List<User> findByFirstNameIgnoreCase(String firstName);
    List<User> findByLastNameIgnoreCase(String lastName);
    List<User> findByPhoneNumber(String phoneNumber);
    List<User> findByIsActive(Boolean isActive);
}