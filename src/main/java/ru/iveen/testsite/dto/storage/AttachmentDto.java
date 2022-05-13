package ru.iveen.testsite.dto.storage;

import lombok.*;

/**
 * @author Polyakov Anton
 * @created 10.05.2022 21:04
 * @project testSite
 */

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AttachmentDto {
    private String id;
    private String filename;
    private Long userId;
    private String path;
}
