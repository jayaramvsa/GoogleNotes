package com.jay.gnotes.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDTO {

    @NotBlank(message = "first name cannot be blank")
    private String firstName;

    @NotBlank(message = "last name cannot be blank")
    private String lastName;

    @Email(message = "email is invalid")
    private String email;

    @Length(min = 5,message = "password should contain minimum 5 characters")
    private String password;

    @Length(min = 10,message = "mobile number should contain min of 10 digits")
    private String mobileNumber;
}
