package ru.iveen.testsite.services.storage;

import ru.iveen.testsite.domain.entity.storage.Attachment;
import ru.iveen.testsite.domain.entity.storage.EType;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface AttachmentService {
    Attachment save(MultipartFile file, EType type, Long userId);

    Attachment findById(String id);

    Resource getResource(String id);

    void deleteAttachment(String id);
}
