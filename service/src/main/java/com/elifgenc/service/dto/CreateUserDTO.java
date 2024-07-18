package com.elifgenc.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.io.Serializable;

@Data
@Log4j2
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String city;
    private String userName;
    private String pass;

}