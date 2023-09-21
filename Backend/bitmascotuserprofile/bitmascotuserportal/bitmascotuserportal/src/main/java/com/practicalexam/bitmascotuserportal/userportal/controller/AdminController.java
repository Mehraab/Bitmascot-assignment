package com.practicalexam.bitmascotuserportal.userportal.controller;

import com.practicalexam.bitmascotuserportal.userportal.delegate.AdminDelegate;
import com.practicalexam.bitmascotuserportal.userportal.dto.AdminResponseDto;
import com.practicalexam.bitmascotuserportal.userportal.dto.LoginDto;
import com.practicalexam.bitmascotuserportal.userportal.dto.UserDto;
import com.practicalexam.bitmascotuserportal.userportal.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminController {

    private final AdminDelegate adminDelegate;

    @Autowired
    public AdminController(AdminDelegate adminDelegate) {
        this.adminDelegate = adminDelegate;
    }

    @PostMapping("/admin-login")
    public @ResponseBody ResponseEntity<List<AdminResponseDto>> adminLogin(@Valid @RequestBody LoginDto loginDto)
        throws UnauthorizedException {
        List<AdminResponseDto> adminResponseDtoList = adminDelegate.loginAdmin(loginDto);
        return ResponseEntity.ok(adminResponseDtoList);
    }
}
