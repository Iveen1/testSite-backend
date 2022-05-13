package ru.iveen.testsite.domain.repository;

import ru.iveen.testsite.domain.entity.user.ERole;
import ru.iveen.testsite.domain.entity.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Polyakov Anton
 * @created 09.05.2022 22:37
 * @project testSite
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(ERole name);
}
