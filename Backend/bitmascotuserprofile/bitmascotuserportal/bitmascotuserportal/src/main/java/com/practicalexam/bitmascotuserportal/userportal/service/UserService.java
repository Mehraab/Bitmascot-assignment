package com.practicalexam.bitmascotuserportal.userportal.service;

import com.practicalexam.bitmascotuserportal.userportal.dto.LoginDto;
import com.practicalexam.bitmascotuserportal.userportal.dto.UserDto;
import com.practicalexam.bitmascotuserportal.userportal.entity.User;
import com.practicalexam.bitmascotuserportal.userportal.exception.EmailNotUniqueException;
import com.practicalexam.bitmascotuserportal.userportal.exception.InvalidException;
import com.practicalexam.bitmascotuserportal.userportal.exception.NotFoundException;
import com.practicalexam.bitmascotuserportal.userportal.exception.UnauthorizedException;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface UserService {

    String registerUser(UserDto userDto) throws EmailNotUniqueException, NoSuchAlgorithmException, UnauthorizedException;

    User loginUser(LoginDto loginDto) throws NotFoundException, InvalidException, NoSuchAlgorithmException;

    List<User> getAllUser();

    List<String> getAllEmail();
}
