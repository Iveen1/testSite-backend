package ru.iveen.testsite.dto.user;

import ru.iveen.testsite.dto.storage.AttachmentDto;
import ru.iveen.testsite.dto.product.ProductDto;
import lombok.*;

import java.util.List;
import java.util.Set;

/**
 * @author Polyakov Anton
 * @created 09.05.2022 23:45
 * @project testSite
 */

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String middleName;
    private Set<RoleDto> roles;
    private AttachmentDto avatar;
    private List<ProductDto> productFavorites;
}
