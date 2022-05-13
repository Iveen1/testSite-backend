package ru.iveen.testsite.domain.mapper;

import ru.iveen.testsite.domain.entity.user.Role;
import ru.iveen.testsite.dto.user.RoleDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDto toDto(Role entity);
    Set<RoleDto> toDto(List<Role> entities);

    // to Entity
    @Mapping(target = "id")
    Role toEntity(RoleDto dto);
    Set<Role> toEntities(List<RoleDto> dtos);

    void update(RoleDto dto, @MappingTarget Role role);
}
