package com.fastcampus.loan.controller;

import com.fastcampus.loan.dto.ApplicationDto;
import com.fastcampus.loan.dto.ApplicationDto.AcceptTerms;
import com.fastcampus.loan.dto.ApplicationDto.Request;
import com.fastcampus.loan.dto.ApplicationDto.Response;
import com.fastcampus.loan.dto.FileDto;
import com.fastcampus.loan.dto.ResponseDTO;
import com.fastcampus.loan.service.ApplicationService;
import com.fastcampus.loan.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@RestController
@RequestMapping("/applications")
public class ApplicationController extends AbstractController{

    private final ApplicationService applicationService;

    private final FileStorageService fileStorageService;

    @PostMapping
    public ResponseDTO<Response> create(@RequestBody Request request) {
        return ok(applicationService.create(request));
    }

    @GetMapping("/{applicationId}")
    public ResponseDTO<Response> get(@PathVariable Long applicationId) {
        return ok(applicationService.get(applicationId));
    }

    @PutMapping("/{applicationId}")
    public ResponseDTO<Response> update(@PathVariable Long applicationId, @RequestBody Request request) {
        return ok(applicationService.update(applicationId, request));
    }

    @DeleteMapping("/{applicationId}")
    public ResponseDTO<Response> delete(@PathVariable Long applicationId) {
        applicationService.delete(applicationId);
        return ok();
    }

    @PostMapping("/{applicationId}/terms")
    public ResponseDTO<Boolean> acceptTerms(@PathVariable Long applicationId, @RequestBody AcceptTerms request) {
        return ok(applicationService.acceptTerms(applicationId, request));
    }

    @PostMapping("/files")
    public ResponseDTO<Void> upload(Long applicationId,MultipartFile file) {
        fileStorageService.save(file);
        return ok();
    }

    @GetMapping("/files")
    public ResponseEntity<Resource> download(Long applicationId,@RequestParam(value= "fileName") String fileName){
        Resource file = fileStorageService.load(applicationId,fileName);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @GetMapping("/files/info")
    public ResponseDTO<List<FileDto>> getFileInfos(Long applicationId) {

        List<FileDto> fileInfos = fileStorageService.loadAll(applicationId).map(path -> {
            String fileName = path.getFileName().toString();
            return FileDto.builder()
                    .name(fileName)
                    .url(MvcUriComponentsBuilder.fromMethodName(ApplicationController.class, "download").build().toString())
                    .build();
        }).collect(Collectors.toList());
        return ok(fileInfos);
    }

    @DeleteMapping("/files")
    public ResponseDTO<Void> deleteAll(Long applicationId) {
        fileStorageService.deleteAll(applicationId);
        return ok();
    }

}
