package ru.iveen.testsite.controllers;

import ru.iveen.testsite.dto.user.UserDto;
import ru.iveen.testsite.pojo.user.PersonalDataRequestPojo;
import ru.iveen.testsite.dto.product.ProductDto;
import ru.iveen.testsite.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

/**
 * @author Polyakov Anton
 * @created 09.05.2022 23:37
 * @project testSite
 */

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/my")
    public UserDto getMy() {
        return userService.getCurrentUserDto();
    }

    @PostMapping("/my/update")
    public ResponseEntity<?> updatePersonalData(@Valid @RequestBody PersonalDataRequestPojo personalDataRequestPojo) {
        userService.updatePersonalData(personalDataRequestPojo);
        return ResponseEntity.ok(Collections.singletonMap("response", "success"));
    }

    @PostMapping("/my/avatar/update")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<?> updateAvatar(@RequestParam String attachmentId) {
        userService.updateAvatar(attachmentId);
        return ResponseEntity.ok(Collections.singletonMap("response", "success"));
    }

    @GetMapping("/my/favorites")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Page<ProductDto> getFavorites(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return userService.getFavorites(page, size);
    }

    @GetMapping("/my/favorites/isFavorite")
    public ResponseEntity<?> isFavorite(@RequestParam Long productId) {
        return ResponseEntity.ok(Collections.singletonMap("response", userService.isProductLiked(productId)));
    }

    @PostMapping("/my/favorites/add")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<?> addFavorites(@RequestParam(defaultValue = "0") Long productId) {
        userService.addProductFavorites(productId);
        return ResponseEntity.ok(Collections.singletonMap("response", "success add product to favorites " + productId));
    }

    @PostMapping("/my/favorites/remove")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<?> removeFavorites(@RequestParam(defaultValue = "0") Long productId) {
        userService.removeProductFavorites(productId);
        return ResponseEntity.ok(Collections.singletonMap("response", "success remove product to favorites " + productId));
    }

    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public Page<UserDto> getUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return userService.findAll(page, size);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public UserDto getUsers(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping("/{userId}/roles/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> removeRole(@PathVariable Long userId, @RequestParam List<Long> rolesIds) {
        System.out.println(rolesIds);
        userService.updateRoles(userId, rolesIds);
        return ResponseEntity.ok(Collections.singletonMap("response", "success"));
    }

}
