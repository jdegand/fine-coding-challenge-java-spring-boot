package com.example.fine_coding_challenge_java_spring_boot.deal_a;

import com.example.fine_coding_challenge_java_spring_boot.deal_base_entity.DealBaseEntity;
import com.example.fine_coding_challenge_java_spring_boot.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "DealAs")
@Getter
@Setter
public class DealA extends DealBaseEntity {

    @Column(nullable = false)
    private Boolean withdrawals;

    @Column(nullable = false)
    private Boolean deposits;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amountInvolved;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id_id")
    private User userId;

}
