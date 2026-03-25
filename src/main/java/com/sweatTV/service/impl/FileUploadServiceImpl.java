package com.sweatTV.service.impl;

import com.sweatTV.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class FileUploadServiceImpl implements FileUploadService {



    @Override
    public String storeVideoFile(MultipartFile file) {
        return "";
    }

    @Override
    public String storeImageFile(MultipartFile file) {
        return "";
    }

    @Override
    public ResponseEntity<Resource> serveVideo(String uuid, String rangeHeader) {
        return null;
    }

    @Override
    public ResponseEntity<Resource> serveImage(String uuid) {
        return null;
    }
}
