package ru.iveen.testsite.pojo.auth;

import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author Polyakov Anton
 * @created 09.05.2022 3:11
 * @project testSite
 */

@Data
public class SignUpRequestPojo {

    @NotBlank
    @Size(min = 5, max = 22)
    private String username;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(max = 64)
    private String firstName;

    @NotBlank
    @Size(max = 64)
    private String lastName;

    @Size(max = 64)
    private String middleName;

    @NotBlank
    @Size(min = 8)
    private String password;
}
