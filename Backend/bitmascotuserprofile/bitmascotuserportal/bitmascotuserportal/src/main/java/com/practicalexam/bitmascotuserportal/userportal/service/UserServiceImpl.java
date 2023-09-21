package com.practicalexam.bitmascotuserportal.userportal.service;

import com.practicalexam.bitmascotuserportal.userportal.dto.LoginDto;
import com.practicalexam.bitmascotuserportal.userportal.dto.UserDto;
import com.practicalexam.bitmascotuserportal.userportal.entity.User;
import com.practicalexam.bitmascotuserportal.userportal.exception.EmailNotUniqueException;
import com.practicalexam.bitmascotuserportal.userportal.exception.InvalidException;
import com.practicalexam.bitmascotuserportal.userportal.exception.NotFoundException;
import com.practicalexam.bitmascotuserportal.userportal.exception.UnauthorizedException;
import com.practicalexam.bitmascotuserportal.userportal.helper.AdminHelper;
import com.practicalexam.bitmascotuserportal.userportal.helper.UserHelper;
import com.practicalexam.bitmascotuserportal.userportal.repository.UserRepository;
import com.practicalexam.bitmascotuserportal.userportal.utils.SecureUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String registerUser(UserDto userDto) throws EmailNotUniqueException, NoSuchAlgorithmException, UnauthorizedException {
        List<User> userListDb = userRepository.findAll();
        boolean isUniqueEmail = UserHelper.checkUniqueEmail(userListDb, userDto.getEmail());
        if (!isUniqueEmail) {
            throw new EmailNotUniqueException("Email already exist. Please provide an unique email address");
        }
        boolean isAdminEmailOrPassword = AdminHelper.checkAdminCredentials(userDto.getEmail(), userDto.getPassword());
        if (isAdminEmailOrPassword) {
            throw new UnauthorizedException("User with this email or password are not allowed to register");
        }
        byte[] salt = SecureUtils.getSalt();
        String hashedPassword = SecureUtils.generateHashedPassword(userDto.getPassword(), salt);
        User newUser = new User(userDto.getFirstName(), userDto.getLastName(), userDto.getAddress(), userDto.getPhone(), userDto.getEmail(), userDto.getBirthDate(), hashedPassword, salt);
        userRepository.saveAndFlush(newUser);
        return "User registered successfully!";
    }

    @Override
    public User loginUser(LoginDto loginDto) throws NotFoundException, InvalidException, NoSuchAlgorithmException {
        User user = userRepository.findByEmail(loginDto.getEmail());
        if (user == null) {
            throw new NotFoundException("No user found with this email address!");
        }
        String actualUserPassword = user.getPassword();
        byte[] salt = user.getSalt();
        String requestedHashedPassword = SecureUtils.generateHashedPassword(loginDto.getPassword(), salt);
        if (!actualUserPassword.equals(requestedHashedPassword)) {
            throw new InvalidException("Wrong password!");
        }

        return user;
    }

    @Override
    public List<User> getAllUser() {
        List<User> allUserList = userRepository.findAll();
        return allUserList;
    }

    @Override
    public List<String> getAllEmail() {
        return userRepository.findAllEMail();
    }


}
