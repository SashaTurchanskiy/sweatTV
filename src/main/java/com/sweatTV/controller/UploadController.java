package com.sweatTV.controller;

import com.sweatTV.service.FileUploadService;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/upload")
@RequiredArgsConstructor
public class UploadController {

    private final FileUploadService fileUploadService;

    @PostMapping("/video")
    public ResponseEntity<Map<String, String>> uploadVideo(@RequestParam("file")MultipartFile file){
        String uuid = fileUploadService.storeVideoFile(file);
        return ResponseEntity.ok(buildUploadResponse(uuid, file));
    }
    @GetMapping("/video/{uuid}")
    public ResponseEntity<Resource> serveVideo(
            @PathVariable String uuid,
            @RequestHeader(value = "range", required = false) String rangeHeader,
            @RequestHeader(value = "token", required = false) String tokenParam){
        return fileUploadService.serveVideo(uuid, rangeHeader);
    }
    private Map<String, String> buildUploadResponse(String uuid, MultipartFile file){
        Map<String, String> response = new HashMap<>();
        response.put("uuid", uuid);
        response.put("filename", file.getOriginalFilename());
        response.put("size", String.valueOf(file.getSize()));
        return response;
    }
}
