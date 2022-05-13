package ru.iveen.testsite.domain.mapper;

import ru.iveen.testsite.domain.entity.storage.Attachment;
import ru.iveen.testsite.dto.storage.AttachmentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AttachmentMapper {
    // to dto
    @Mapping(target = "filename", expression = "java(entity.getFilename())")
    @Mapping(target = "path", expression = "java(entity.getPath())")
    AttachmentDto toDto(Attachment entity);
    List<AttachmentDto> toDto(List<Attachment> entities);
}
