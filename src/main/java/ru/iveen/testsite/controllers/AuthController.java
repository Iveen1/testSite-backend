package ru.iveen.testsite.controllers;

import ru.iveen.testsite.pojo.auth.SignInRequestPojo;
import ru.iveen.testsite.pojo.auth.SignUpRequestPojo;
import ru.iveen.testsite.services.user.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Polyakov Anton
 * @created 09.05.2022 3:55
 * @project testSite
 */

@RestController
@RequestMapping(value = "/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<?> authUser(@Valid @RequestBody SignInRequestPojo signInRequestPojo) {
        return ResponseEntity.ok(authService.authenticate(signInRequestPojo));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequestPojo signUpRequestPojo) {
        return ResponseEntity.ok(authService.register(signUpRequestPojo));
    }
}
