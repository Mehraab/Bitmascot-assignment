package com.practicalexam.bitmascotuserportal.userportal.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "NAME_NAME")
    private String lastName;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "BIRTH_DATE")
    private LocalDate birthDate;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "SALT", length = 16)
    private byte[] salt;

    public User(String firstName, String lastName, String address, String phone, String email, LocalDate birthDate, String password, byte[] salt) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.birthDate = birthDate;
        this.password = password;
        this.salt = salt;
    }
}
