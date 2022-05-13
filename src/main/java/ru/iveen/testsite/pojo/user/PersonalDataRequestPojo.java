package ru.iveen.testsite.pojo.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author Polyakov Anton
 * @created 10.05.2022 16:32
 * @project testSite
 */
@Data
@AllArgsConstructor
public class PersonalDataRequestPojo {
    @NotBlank
    @Size(max = 64)
    private String firstName;

    @NotBlank
    @Size(max = 64)
    private String lastName;

    @Size(max = 64)
    private String middleName;
}
