package com.practicalexam.bitmascotuserportal.userportal.delegate;

import com.practicalexam.bitmascotuserportal.userportal.dto.AdminResponseDto;
import com.practicalexam.bitmascotuserportal.userportal.dto.LoginDto;
import com.practicalexam.bitmascotuserportal.userportal.dto.UserDto;
import com.practicalexam.bitmascotuserportal.userportal.exception.UnauthorizedException;

import java.util.List;

public interface AdminDelegate {

    List<AdminResponseDto> loginAdmin(LoginDto loginDto) throws UnauthorizedException;
}
