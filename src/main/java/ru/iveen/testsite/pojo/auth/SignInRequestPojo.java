package ru.iveen.testsite.pojo.auth;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
* @created 09.05.2022 0:09
* @project testSite
* @author Polyakov Anton
*/

@Data
public class SignInRequestPojo {

    @NotBlank
    @Size(min = 5, max = 22)
    private String username;

    @NotBlank
    @Size(min = 8)
    private String password;
}
