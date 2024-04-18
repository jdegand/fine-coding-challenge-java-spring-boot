package com.example.fine_coding_challenge_java_spring_boot.deal_a;

import com.example.fine_coding_challenge_java_spring_boot.user.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DealARepository extends JpaRepository<DealA, Long> {

    DealA findFirstByUserId(User user);

}
