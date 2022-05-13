package ru.iveen.testsite.dto.product;

import ru.iveen.testsite.dto.storage.AttachmentDto;
import ru.iveen.testsite.dto.user.UserDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;

/**
 * @author Polyakov Anton
 * @created 11.05.2022 1:48
 * @project testSite
 */

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private String title;
    @JsonIgnore
    private List<UserDto> userFavorites;
    private AttachmentDto photo;
}
