package com.practicalexam.bitmascotuserportal.userportal.delegate;

import com.practicalexam.bitmascotuserportal.userportal.dto.LoginDto;
import com.practicalexam.bitmascotuserportal.userportal.dto.UserDto;
import com.practicalexam.bitmascotuserportal.userportal.entity.User;
import com.practicalexam.bitmascotuserportal.userportal.exception.EmailNotUniqueException;
import com.practicalexam.bitmascotuserportal.userportal.exception.InvalidException;
import com.practicalexam.bitmascotuserportal.userportal.exception.NotFoundException;
import com.practicalexam.bitmascotuserportal.userportal.exception.UnauthorizedException;
import com.practicalexam.bitmascotuserportal.userportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Component
public class UserDelegateImpl implements UserDelegate{

    private final UserService userService;

    @Autowired
    public UserDelegateImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String registerUser(UserDto userDto) throws EmailNotUniqueException, NoSuchAlgorithmException, UnauthorizedException {
        return userService.registerUser(userDto);
    }

    @Override
    public UserDto loginUser(LoginDto loginDto) throws NotFoundException, InvalidException, NoSuchAlgorithmException {
        User user = userService.loginUser(loginDto);
        return new UserDto(user.getUserId(), user.getFirstName(), user.getLastName(), user.getAddress(), user.getPhone(), user.getEmail(), user.getBirthDate());
    }

    @Override
    public List<String> getAllEmail(){
        return userService.getAllEmail();
    }
}
