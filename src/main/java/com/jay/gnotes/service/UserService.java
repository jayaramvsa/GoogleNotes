package com.jay.gnotes.service;

import com.jay.gnotes.dto.UserDTO;

public interface UserService {

    void registerUser(UserDTO userDto);

    void verifyEmail(String token);
}
