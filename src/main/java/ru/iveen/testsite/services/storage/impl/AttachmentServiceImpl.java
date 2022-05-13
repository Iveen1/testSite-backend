package ru.iveen.testsite.services.storage.impl;

import ru.iveen.testsite.domain.entity.storage.Attachment;
import ru.iveen.testsite.domain.entity.storage.EType;
import ru.iveen.testsite.domain.repository.AttachmentRepository;
import ru.iveen.testsite.services.storage.AttachmentService;
import ru.iveen.testsite.services.storage.StorageService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FilenameUtils;

import javax.transaction.Transactional;

/**
 * @author Polyakov Anton
 * @created 10.05.2022 18:26
 * @project testSite
 */
@Service
@AllArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {
    private final AttachmentRepository attachmentRepository;
    private final StorageService storageService;

    @Override
    public Attachment save(MultipartFile file, EType type, Long userId) {
        System.out.println(FilenameUtils.getExtension(file.getOriginalFilename()));
        Attachment attachment = new Attachment(FilenameUtils.getExtension(file.getOriginalFilename()), type, userId);
        System.out.println(attachment.getFilename());
        attachment.setPath(storageService.store(file, attachment.getFilename()));
        return attachmentRepository.save(attachment);
    }

    @Override
    public Attachment findById(String id) {
        return attachmentRepository.findById(id).orElseThrow(() -> new RuntimeException(id));
    }

    @Override
    public Resource getResource(String id) {
        Attachment attachment = this.findById(id);
        return storageService.loadAsResource(attachment.getFilename());
    }


    @Transactional
    @Override
    public void deleteAttachment(String id) {
        Attachment attachment = this.findById(id);
        attachmentRepository.delete(attachment);
        storageService.deleteByResource(attachment.getFilename());
    }
}
