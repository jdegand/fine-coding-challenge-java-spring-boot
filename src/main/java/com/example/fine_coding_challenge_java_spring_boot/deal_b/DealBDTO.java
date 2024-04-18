package com.example.fine_coding_challenge_java_spring_boot.deal_b;

import com.example.fine_coding_challenge_java_spring_boot.deal_base_entity.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DealBDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String name;

    @NotNull
    @Size(max = 255)
    private String description;

    @NotNull
    private Status status;

    @NotNull
    @Digits(integer = 10, fraction = 2)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal grossSalary;

    @NotNull
    private LocalDate contractStartDate;

    private Integer userId;

}
