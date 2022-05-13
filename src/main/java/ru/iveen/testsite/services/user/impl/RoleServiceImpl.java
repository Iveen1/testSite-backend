package ru.iveen.testsite.services.user.impl;

import ru.iveen.testsite.domain.entity.user.Role;
import ru.iveen.testsite.domain.mapper.RoleMapper;
import ru.iveen.testsite.domain.repository.RoleRepository;
import ru.iveen.testsite.dto.user.RoleDto;
import ru.iveen.testsite.services.user.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

/**
 * @author Polyakov Anton
 * @created 10.05.2022 3:11
 * @project testSite
 */

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public RoleDto findById(Long id) {
        return roleMapper.toDto(roleRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role with id " + id + " not found.")));
    }

    @Override
    public Set<RoleDto> findByIds(List<Long> ids) {
        List<Role> roles = roleRepository.findAllById(ids);
        if (roles.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Roles with id " + ids + " not found.");
        }
        return roleMapper.toDto(roles);
    }
}
