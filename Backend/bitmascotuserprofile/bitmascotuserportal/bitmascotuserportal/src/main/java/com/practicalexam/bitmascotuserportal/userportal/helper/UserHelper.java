package com.practicalexam.bitmascotuserportal.userportal.helper;

import com.practicalexam.bitmascotuserportal.userportal.entity.User;

import java.util.List;

public class UserHelper {

    public static boolean checkUniqueEmail(List<User> userList, String newEmail){

        for(User user: userList) {
            String userEmail = user.getEmail();
            if (userEmail.equals(newEmail)){
                return false;
            }
        }
        return true;
    }
}
