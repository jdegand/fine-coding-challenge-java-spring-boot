package com.example.fine_coding_challenge_java_spring_boot.user;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByEmailAddressIgnoreCase(String emailAddress);

}
