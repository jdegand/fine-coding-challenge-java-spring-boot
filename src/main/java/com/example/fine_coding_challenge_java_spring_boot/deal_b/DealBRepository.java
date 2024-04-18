package com.example.fine_coding_challenge_java_spring_boot.deal_b;

import com.example.fine_coding_challenge_java_spring_boot.user.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DealBRepository extends JpaRepository<DealB, Long> {

    DealB findFirstByUserId(User user);

}
