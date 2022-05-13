package ru.iveen.testsite.services.user;

import ru.iveen.testsite.dto.product.ProductDto;
import ru.iveen.testsite.domain.entity.user.User;
import ru.iveen.testsite.dto.user.UserDto;
import ru.iveen.testsite.pojo.user.PersonalDataRequestPojo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    void save(User user);

    User getCurrentUser();

    UserDto getCurrentUserDto();

    Page<ProductDto> getFavorites(int page, int size);

    boolean isProductLiked(Long productId);

    UserDto findByUsername(String username);

    UserDto findById(Long id);

    void updateAvatar(String attachmentId);

    void updatePersonalData(PersonalDataRequestPojo personalDataRequestPojo);

    void updateRoles(Long userId, List<Long> rolesIds);

    void addProductFavorites(Long productId);

    void removeProductFavorites(Long productId);

    void delete(Long id);

    Page<UserDto> findAll(int page, int size);
}
