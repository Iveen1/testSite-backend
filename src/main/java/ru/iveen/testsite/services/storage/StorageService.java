package ru.iveen.testsite.services.storage;

import org.springframework.web.multipart.MultipartFile;

import org.springframework.core.io.Resource;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {
    void init();

    String store(MultipartFile file, String filename);

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();

    void deleteByResource(String filename);
//    void upload(MultipartFile multipartFile);
}
