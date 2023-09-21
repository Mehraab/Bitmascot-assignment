package com.practicalexam.bitmascotuserportal.userportal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private static final String PHONE_NUMBER_PATTERN = "^(\\+?880|0)(\\d){10}$";

    private Long id;

    @NotEmpty(message = "First name can not be empty!")
    private String firstName;

    @NotEmpty(message = "first name can not be empty!")
    private String lastName;

    @NotEmpty(message = "Address can not be empty!")
    private String address;

    @NotEmpty(message = "Address can not be empty!")
    @Pattern(regexp = PHONE_NUMBER_PATTERN, message = "Not a valid Bangladeshi phone number!")
    @Size(min = 11, max = 14, message = "Phone number must be between 11 and 14 characters")
    private String phone;

    @NotEmpty(message = "Email can not be empty!")
    @Email(message = "Not a valid email!")
    private String email;

    @Past(message = "Birth date must be in the past")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @NotEmpty(message = "Password must not be empty")
    @Length(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
    private String password;

    public UserDto(Long id, String firstName, String lastName, String address, String phone, String email,
        LocalDate birthDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.birthDate = birthDate;
    }
}
