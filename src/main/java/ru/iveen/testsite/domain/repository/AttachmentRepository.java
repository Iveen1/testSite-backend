package ru.iveen.testsite.domain.repository;

import ru.iveen.testsite.domain.entity.storage.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<Attachment, String> {

}
