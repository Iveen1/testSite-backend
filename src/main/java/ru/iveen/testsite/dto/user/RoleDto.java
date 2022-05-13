package ru.iveen.testsite.dto.user;

import ru.iveen.testsite.domain.entity.user.ERole;
import lombok.*;

/**
 * @author Polyakov Anton
 * @created 10.05.2022 0:41
 * @project testSite
 */

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
    private Long id;
    private ERole name;
}
