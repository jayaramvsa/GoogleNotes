package com.jay.gnotes.service;

import com.jay.gnotes.dto.UserDTO;
import com.jay.gnotes.entity.User;
import com.jay.gnotes.exception.GnotesException;
import com.jay.gnotes.repository.UserRepository;
import com.jay.gnotes.utils.EmailService;
import com.jay.gnotes.utils.TokenService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private EmailService emailService;

    @Value("${verify.url}")
    private String verifyUrl;

    @Override
    public void registerUser(UserDTO userDto) {

        Optional<User> userCheck = userRepository.findByEmail(userDto.getEmail());
        if (userCheck.isPresent()) throw new GnotesException(HttpStatus.CONFLICT.value(), "User Already Exist");
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User savedUser = userRepository.save(user);
        String token = tokenService.createToken(savedUser.getId());
        emailService.sendEmail(savedUser.getEmail(), "verify Email", verifyUrl + token);

    }

    @Override
    public void verifyEmail(String token) {
        Long id = tokenService.decodeToken(token);
        User user = userRepository.findById(id).orElseThrow(() -> new GnotesException(HttpStatus.NOT_FOUND.value(), "User Not Found"));
        user.setVerified(true);
        userRepository.save(user);
    }
}
