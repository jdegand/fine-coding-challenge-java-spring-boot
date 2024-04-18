package com.example.fine_coding_challenge_java_spring_boot.lead;

import com.example.fine_coding_challenge_java_spring_boot.base_entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "Leads")
@Getter
@Setter
public class Lead extends BaseEntity {
}
