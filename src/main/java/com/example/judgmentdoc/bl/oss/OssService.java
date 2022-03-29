package com.example.judgmentdoc.bl.oss;

import org.springframework.web.multipart.MultipartFile;

public interface OssService {
    String upload(MultipartFile multipartFile, String fileName);

    void delete(String fileName);
}
