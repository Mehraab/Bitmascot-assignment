package com.practicalexam.bitmascotuserportal.userportal.service;

import com.practicalexam.bitmascotuserportal.userportal.dto.LoginDto;
import com.practicalexam.bitmascotuserportal.userportal.entity.User;
import com.practicalexam.bitmascotuserportal.userportal.exception.UnauthorizedException;

import java.util.List;

public interface AdminService {

    List<User> adminLogin(LoginDto loginDto) throws UnauthorizedException;
}
