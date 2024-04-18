package com.example.fine_coding_challenge_java_spring_boot.user;

import com.example.fine_coding_challenge_java_spring_boot.base_entity.BaseEntity;
import com.example.fine_coding_challenge_java_spring_boot.deal_a.DealA;
import com.example.fine_coding_challenge_java_spring_boot.deal_b.DealB;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "Users")
@Getter
@Setter
public class User extends BaseEntity {

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String court;

    @OneToMany(mappedBy = "userId")
    private Set<DealA> dealAs;

    @OneToMany(mappedBy = "userId")
    private Set<DealB> dealBs;

}
