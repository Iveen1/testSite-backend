package ru.iveen.testsite.domain.mapper;

import ru.iveen.testsite.domain.entity.user.User;
import ru.iveen.testsite.dto.user.UserDto;
import ru.iveen.testsite.pojo.user.PersonalDataRequestPojo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * @author Polyakov Anton
 * @created 09.05.2022 23:56
 * @project testSite
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    // to dto
    @Mapping(target = "productFavorites", ignore = true)
    UserDto toDto(User entity);
    List<UserDto> toDto(List<User> entities);

    // to Entity
    @Mapping(target = "id", ignore = true)
    User toEntity(UserDto dto);
    List<User> toEntities(List<UserDto> dtos);

    @Mapping(target = "id", ignore = true)
    void update(UserDto dto, @MappingTarget User user);

    void update(PersonalDataRequestPojo pojo, @MappingTarget User user);
}
