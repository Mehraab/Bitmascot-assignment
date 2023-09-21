package com.practicalexam.bitmascotuserportal.userportal.service;

import com.practicalexam.bitmascotuserportal.userportal.dto.LoginDto;
import com.practicalexam.bitmascotuserportal.userportal.entity.User;
import com.practicalexam.bitmascotuserportal.userportal.exception.UnauthorizedException;
import com.practicalexam.bitmascotuserportal.userportal.helper.AdminHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{

    private final UserService userService;

    @Autowired
    public AdminServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<User> adminLogin(LoginDto loginDto) throws UnauthorizedException {
        boolean isValidAdminCredentials = AdminHelper.checkAdminCredentials(loginDto.getEmail(), loginDto.getPassword());
        if (!isValidAdminCredentials){
            throw new UnauthorizedException("You are not authorized to login admin panel!");
        }
        return userService.getAllUser();

    }
}
