package app.ordering.food.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface MinioService {
    List<Map<String, Object>> list(String bucket);
    void makeBucket(String bucket);
    byte[] download(String bucket, String filename);
    boolean upload(String bucket, MultipartFile multipartFile, String filename);
    boolean existObject(String bucket, String filename);
    boolean removeObject(String bucket, String filename);
    boolean removeBucket(String bucket);
    String downloadBase64(String bucket, String filename);
}
