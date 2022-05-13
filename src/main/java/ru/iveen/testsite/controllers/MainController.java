package ru.iveen.testsite.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
* @created 05.05.2022 22:36
* @project testSite
* @author Polyakov Anton
*/

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class MainController {

    @GetMapping("/greeting")
    public String greeting() {
        return "greeting";
    }

    @GetMapping("/authorized")
    @PreAuthorize("hasRole('USER')")
    public String authorized() {
        return "ure authorized";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String admin() {
        return "ure admin";
    }
}
