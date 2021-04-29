package com.jay.gnotes.controller;


import com.jay.gnotes.dto.UserDTO;
import com.jay.gnotes.response.Response;
import com.jay.gnotes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/register")
    public ResponseEntity<Response> register(@Valid @RequestBody UserDTO userDTO, BindingResult result) {

        if (result.hasErrors()) {
            return new ResponseEntity<Response>(new Response(HttpStatus.UNPROCESSABLE_ENTITY.value(),
                    result.getAllErrors().get(0).getDefaultMessage(), ""), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        userService.registerUser(userDTO);
        return new ResponseEntity<Response>(new Response(HttpStatus.CREATED.value(), "User Created Successfully", ""), HttpStatus.CREATED);
    }

    @PutMapping(value = "/verify/{token}")
    public  ResponseEntity<Response> verifyEMail(@PathVariable String token){
        userService.verifyEmail(token);
        return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), "Email Verified",""),HttpStatus.OK);
    }
}
