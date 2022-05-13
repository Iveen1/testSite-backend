package ru.iveen.testsite.controllers;

import ru.iveen.testsite.domain.entity.storage.Attachment;
import ru.iveen.testsite.domain.entity.storage.EType;
import ru.iveen.testsite.domain.mapper.AttachmentMapper;
import ru.iveen.testsite.dto.storage.AttachmentDto;
import ru.iveen.testsite.services.storage.AttachmentService;
import ru.iveen.testsite.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Polyakov Anton
 * @created 10.05.2022 3:31
 * @project testSite
 */


@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/storage")
@RequiredArgsConstructor
@RestController
public class AttachmentController {
    private final AttachmentService attachmentService;
    private final AttachmentMapper attachmentMapper;
    private final UserService userService;

    @PostMapping("/upload")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public AttachmentDto upload(@RequestParam MultipartFile file, @RequestParam EType type) {

        Attachment obj = attachmentService.save(file, type, userService.getCurrentUser().getId());
        System.out.println("im in attachment");
        System.out.println(obj.getPath());
        System.out.println("leave");
        return attachmentMapper.toDto(obj);
    }

    @GetMapping("/get/base64")
    public Map<Object, Object> getBase64(@RequestParam String id) throws IOException {
        Resource file = attachmentService.getResource(id);
        byte[] fileBytes = IOUtils.toByteArray(file.getInputStream());
        Map<Object, Object> map = new HashMap<>();
        map.put("data", Base64.getEncoder().encodeToString(fileBytes));
        return map;
    }

    @GetMapping("/get")
    public ResponseEntity<Resource> get(@RequestParam String id){
        Resource file = attachmentService.getResource(id);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @GetMapping("/remove")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> remove(@RequestParam String id) {
        attachmentService.deleteAttachment(id);
        return ResponseEntity.ok("success remove");
    }
}
