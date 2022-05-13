package ru.iveen.testsite.pojo.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author Polyakov Anton
 * @created 09.05.2022 3:15
 * @project testSite
 */

@Data
@AllArgsConstructor
public class JwtResponsePojo {
    private String token;
    private String username;
    private List<String> roles;
}
