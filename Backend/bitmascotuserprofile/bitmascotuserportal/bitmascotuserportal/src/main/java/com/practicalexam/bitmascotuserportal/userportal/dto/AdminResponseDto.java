package com.practicalexam.bitmascotuserportal.userportal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AdminResponseDto {
    private String name;
    private int age;
    private String email;
    private String phone;

    public static String getFullName(String firstName, String lastName){
        return firstName + " " + lastName;
    }
}
