package com.fastcampus.loan.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface FileStorageService {

    void save(Long applicationId,MultipartFile file);


    Resource load(Long applicationId,String filename);

    Stream<Path> loadAll(Long applicationId);

    void deleteAll(Long applicationId);

}
