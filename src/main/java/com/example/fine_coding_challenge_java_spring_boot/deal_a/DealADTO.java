package com.example.fine_coding_challenge_java_spring_boot.deal_a;

import com.example.fine_coding_challenge_java_spring_boot.deal_base_entity.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DealADTO {

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
    private Boolean withdrawals;

    @NotNull
    private Boolean deposits;

    @NotNull
    @Digits(integer = 10, fraction = 2)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal amountInvolved;

    private Integer userId;

}
