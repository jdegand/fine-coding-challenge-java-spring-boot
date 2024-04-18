package com.example.fine_coding_challenge_java_spring_boot.lead;

import org.springframework.data.jpa.repository.JpaRepository;


public interface LeadRepository extends JpaRepository<Lead, Integer> {

    boolean existsByEmailAddressIgnoreCase(String emailAddress);

}
