package com.udacity.jwdnd.course1.cloudstorage.services.impl;

import com.udacity.jwdnd.course1.cloudstorage.constants.CommonMsg;
import com.udacity.jwdnd.course1.cloudstorage.entity.User;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.services.HashService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final HashService hashService;

    public UserServiceImpl(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }

    @Override
    public User getUser(String username) {
        return userMapper.getUser(username);
    }

    @Override
    public String signUpUser(User user) {
        if(null != userMapper.getUser(user.getUsername())) {
            return CommonMsg.EXISTED_USER;
        } else {
            if (this.createNewUser(user)) {
                return null;
            } else return CommonMsg.ACTION_FAIL;
        }
    }

    private boolean createNewUser(User user) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        String encodeSalt = Base64.getEncoder().encodeToString(salt);
        String passwordHashed = hashService.getHashedValue(user.getPassword(), encodeSalt);
        User newUser = new User(null, user.getUsername(), passwordHashed, encodeSalt, user.getFirstName(), user.getLastName());
        return 0 < userMapper.insertUser(newUser);
    }

}
