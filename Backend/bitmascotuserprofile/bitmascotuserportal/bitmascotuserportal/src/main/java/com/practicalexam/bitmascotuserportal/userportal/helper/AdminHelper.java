package com.practicalexam.bitmascotuserportal.userportal.helper;

import com.practicalexam.bitmascotuserportal.userportal.constant.AdminConstant;

import java.time.LocalDate;
import java.time.Period;

public class AdminHelper {

    public static boolean checkAdminCredentials(String requestedEmail, String requestedPassword){

        if (requestedEmail.equals(AdminConstant.ADMIN_EMAIL) && requestedPassword.equals(AdminConstant.ADMIN_PASSWORD)){
            return true;
        }
        return false;
    }

}
