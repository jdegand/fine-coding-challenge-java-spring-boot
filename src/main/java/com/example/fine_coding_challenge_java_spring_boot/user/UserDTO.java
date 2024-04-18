package com.example.fine_coding_challenge_java_spring_boot.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserDTO {

    private Integer id;

    @NotNull
    @Size(max = 255)
    private String firstName;

    @NotNull
    @Size(max = 255)
    private String lastName;

    @NotNull
    @Size(max = 255)
    private String address;

    @NotNull
    @Size(max = 255)
    private String city;

    @NotNull
    @Size(max = 255)
    private String zipCode;

    @NotNull
    @Size(max = 255)
    private String phoneNumber;

    @NotNull
    @Size(max = 255)
    @UserEmailAddressUnique
    private String emailAddress;

    @NotNull
    @Size(max = 255)
    private String state;

    @NotNull
    @Size(max = 255)
    private String court;

}
