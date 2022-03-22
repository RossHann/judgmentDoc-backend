package com.example.judgmentdoc.util.aliOss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

public class AliOssUtil {
    private final static String ENDPOINT = "oss-cn-shanghai.aliyuncs.com";
    private final static String ACCESS_KEY_ID = "LTAI4G3YDVKD453rLQRPeAQj";
    private final static String ACCESS_KEY_SECRET = "zHDmPtPZ6lzTfHWIuMnxdrYvEcSkHM";
    private final static String BUCKET_NAME = "pyyybf";
    private final static String DIR_PREFIX = "judgmentDoc/";

    public static void upload(MultipartFile multipartFile, String fileName) {
        OSS ossClient = new OSSClientBuilder().build(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        try {
            InputStream multipartFileInputStream = multipartFile.getInputStream();
            PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET_NAME, DIR_PREFIX + fileName, multipartFileInputStream);
            ossClient.putObject(putObjectRequest);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();  //关闭流
        }
    }

    public static void delete(String fileName) {
        OSS ossClient = new OSSClientBuilder().build(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        ossClient.deleteObject(BUCKET_NAME, DIR_PREFIX + fileName);
        ossClient.shutdown();
    }
}
