package ru.iveen.testsite.services.user.impl;

import ru.iveen.testsite.domain.entity.product.Product;
import ru.iveen.testsite.domain.entity.user.User;
import ru.iveen.testsite.domain.mapper.ProductMapper;
import ru.iveen.testsite.domain.mapper.UserMapper;
import ru.iveen.testsite.domain.repository.UserRepository;
import ru.iveen.testsite.dto.product.ProductDto;
import ru.iveen.testsite.dto.user.RoleDto;
import ru.iveen.testsite.dto.user.UserDto;
import ru.iveen.testsite.pojo.user.PersonalDataRequestPojo;
import ru.iveen.testsite.services.product.ProductService;
import ru.iveen.testsite.services.storage.AttachmentService;
import ru.iveen.testsite.services.user.RoleService;
import ru.iveen.testsite.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

/**
* @created 08.05.2022 21:36
* @project testSite
* @author Polyakov Anton
*/

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final ProductService productService;
    private final AttachmentService attachmentService;
    private final UserMapper userMapper;
    private final ProductMapper productMapper;

    @Override
    public Page<UserDto> findAll(int page, int size) {
        Page<User> entityPage = userRepository.findAll(PageRequest.of(page, size));

        return new PageImpl<>(
                userMapper.toDto(entityPage.toList()),
                PageRequest.of(page, size),
                entityPage.getTotalElements()
        );
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public UserDto findByUsername(String username) {
        return userMapper.toDto(userRepository.findByUsername(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with username " + username + " not found.")));
    }

    @Override
    public UserDto findById(Long id) {
        return userMapper.toDto(userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + id + " not found.")));
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with username " + username + " not found."));
    }

    @Override
    public UserDto getCurrentUserDto() {
        return userMapper.toDto(this.getCurrentUser());
    }

    @Override
    public Page<ProductDto> getFavorites(int page, int size){
        List<Product> products = this.getCurrentUser().getProductFavorites();
        for (Product product: products) {
            System.out.println(product.getName());
        }
        Page<Product> entityPage = new PageImpl<>(products, PageRequest.of(page, size), products.size());

        return new PageImpl<>(
                productMapper.toDto(entityPage.toList()),
                PageRequest.of(page, size),
                entityPage.getTotalElements()
        );
    }

    @Override
    public boolean isProductLiked(Long productId) {
        List<Product> products = this.getCurrentUser().getProductFavorites();
        return products.stream().anyMatch(item -> item.getId().equals(productId));
    }

    @Override
    public void addProductFavorites(Long productId) {
        User user = getCurrentUser();
        Product product = productService.findById(productId);
        List<Product> newProductFavorites = user.getProductFavorites();

        if (newProductFavorites.contains(product)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already added this product to favorites: " + product.getName());
        }
        newProductFavorites.add(product);
        this.save(user);
    }

    @Override
    public void removeProductFavorites(Long productId) {
        User user = getCurrentUser();
        Product product = productService.findById(productId);
        List<Product> newProductFavorites = user.getProductFavorites();
        if (!newProductFavorites.contains(product)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already haven't this product to favorites: " + product.getName());
        }
        newProductFavorites.remove(product);
        this.save(user);
    }

    @Override
    public void updateRoles(Long userId, List<Long> rolesIds) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + userId + " not found."));
        UserDto userDto = this.findById(userId);
        Set<RoleDto> newRoles = roleService.findByIds(rolesIds);
        userDto.setRoles(newRoles);
        userMapper.update(userDto, user);
        this.save(user);
    }

    @Override
    public void updateAvatar(String attachmentId) {
        User user = getCurrentUser();
        user.setAvatar(attachmentService.findById(attachmentId));
        this.save(user);
    }

    @Override
    public void updatePersonalData(PersonalDataRequestPojo personalDataRequestPojo) {
        User user = getCurrentUser();
        userMapper.update(personalDataRequestPojo, user);
        this.save(user);
    }
}
