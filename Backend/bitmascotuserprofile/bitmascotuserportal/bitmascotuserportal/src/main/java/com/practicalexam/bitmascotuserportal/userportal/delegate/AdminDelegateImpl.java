package com.practicalexam.bitmascotuserportal.userportal.delegate;

import com.practicalexam.bitmascotuserportal.userportal.dto.AdminResponseDto;
import com.practicalexam.bitmascotuserportal.userportal.dto.LoginDto;
import com.practicalexam.bitmascotuserportal.userportal.entity.User;
import com.practicalexam.bitmascotuserportal.userportal.exception.UnauthorizedException;
import com.practicalexam.bitmascotuserportal.userportal.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Component
public class AdminDelegateImpl implements AdminDelegate{
    private final AdminService adminService;

    @Autowired
    public AdminDelegateImpl(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public List<AdminResponseDto> loginAdmin(LoginDto loginDto) throws UnauthorizedException {
        List<User> allUsers = adminService.adminLogin(loginDto);
        List<AdminResponseDto> adminResponseDtoList = new ArrayList<>();
        allUsers.forEach(user -> {
            AdminResponseDto adminResponseDto = new AdminResponseDto(AdminResponseDto.getFullName(user.getFirstName(), user.getLastName()), calculateAge(user.getBirthDate()), user.getEmail(), user.getPhone());
            adminResponseDtoList.add(adminResponseDto);
        });
        return adminResponseDtoList;
    }

    private int calculateAge(LocalDate birthDate){
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(birthDate, currentDate);
        return period.getYears();
    }
}
