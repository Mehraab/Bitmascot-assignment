package com.practicalexam.bitmascotuserportal.userportal.controller;

import com.practicalexam.bitmascotuserportal.userportal.delegate.UserDelegate;
import com.practicalexam.bitmascotuserportal.userportal.dto.LoginDto;
import com.practicalexam.bitmascotuserportal.userportal.dto.UserDto;
import com.practicalexam.bitmascotuserportal.userportal.exception.EmailNotUniqueException;
import com.practicalexam.bitmascotuserportal.userportal.exception.InvalidException;
import com.practicalexam.bitmascotuserportal.userportal.exception.NotFoundException;
import com.practicalexam.bitmascotuserportal.userportal.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserDelegate userDelegate;

    @Autowired
    public UserController(UserDelegate userDelegate) {
        this.userDelegate = userDelegate;
    }

    @GetMapping("/all-email")
    public @ResponseBody ResponseEntity<List<String>> getAllEmail(){
        List<String> allEmail = userDelegate.getAllEmail();
        return ResponseEntity.ok(allEmail);
    }
    @PostMapping("/user-register")
    public @ResponseBody ResponseEntity<String> registerUser( @Valid @RequestBody UserDto userDto)
            throws EmailNotUniqueException, NoSuchAlgorithmException, UnauthorizedException {
        String message = userDelegate.registerUser(userDto);
        return ResponseEntity.ok(message);
    }

    @PostMapping("/user-login")
    public @ResponseBody ResponseEntity<UserDto> loginUser(@Valid @RequestBody LoginDto loginDto)
        throws NotFoundException, InvalidException, NoSuchAlgorithmException {
        UserDto userDto = userDelegate.loginUser(loginDto);
        return ResponseEntity.ok(userDto);
    }

}
