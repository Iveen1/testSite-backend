package ru.iveen.testsite.services.user;

import ru.iveen.testsite.dto.user.RoleDto;

import java.util.List;
import java.util.Set;

public interface RoleService {
    RoleDto findById(Long id);
    Set<RoleDto> findByIds(List<Long> ids);
}
