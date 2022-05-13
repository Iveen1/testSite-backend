package ru.iveen.testsite.services.user;

import ru.iveen.testsite.config.jwt.JwtUtils;
import ru.iveen.testsite.domain.entity.user.ERole;
import ru.iveen.testsite.domain.entity.user.Role;
import ru.iveen.testsite.domain.entity.user.User;
import ru.iveen.testsite.domain.repository.RoleRepository;
import ru.iveen.testsite.domain.repository.UserRepository;
import ru.iveen.testsite.pojo.auth.JwtResponsePojo;
import ru.iveen.testsite.pojo.auth.MessageResponsePojo;
import ru.iveen.testsite.pojo.auth.SignInRequestPojo;
import ru.iveen.testsite.pojo.auth.SignUpRequestPojo;
import ru.iveen.testsite.services.user.impl.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Polyakov Anton
 * @created 10.05.2022 6:11
 * @project testSite
 */

@Service
@AllArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public JwtResponsePojo authenticate(@RequestBody SignInRequestPojo signInRequestPojo){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequestPojo.getUsername(), signInRequestPojo.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtils.generateToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        return new JwtResponsePojo(token, userDetails.getUsername(), roles);
    }

    public ResponseEntity<?> register(@RequestBody SignUpRequestPojo signUpRequestPojo){
        if (userRepository.existsByEmail(signUpRequestPojo.getEmail())){
            return ResponseEntity.badRequest().body(new MessageResponsePojo("Email already exists"));
        }

        if (userRepository.existsByUsername(signUpRequestPojo.getUsername())){
            return ResponseEntity.badRequest().body(new MessageResponsePojo("Username already exists"));
        }

        User user = new User();
        Set<Role> roles = new HashSet<>();
        user.setUsername(signUpRequestPojo.getUsername());
        user.setEmail(signUpRequestPojo.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequestPojo.getPassword()));
        user.setFirstName(signUpRequestPojo.getFirstName());
        user.setLastName(signUpRequestPojo.getLastName());
        user.setMiddleName(signUpRequestPojo.getMiddleName());
        user.setUsername(signUpRequestPojo.getUsername());
        roles.add(roleRepository.findByName(ERole.ROLE_USER));
        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponsePojo("User " + user.getUsername() + " created!!!"));
    }
}
