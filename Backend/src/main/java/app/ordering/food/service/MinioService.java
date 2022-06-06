package app.ordering.food.service;

import app.ordering.food.common.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface MinioService {
    Result<List<Map<String, Object>>> list(String bucket);
    void makeBucket(String bucket);
    ResponseEntity<byte[]> download(String bucket, String filename);
    boolean upload(String bucket, MultipartFile multipartFile, String filename);
    boolean existObject(String bucket, String filename);
    void removeObject(String bucket, String filename);
    void removeBucket(String bucket);
}