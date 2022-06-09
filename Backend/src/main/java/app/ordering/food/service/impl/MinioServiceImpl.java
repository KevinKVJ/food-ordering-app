package app.ordering.food.service.impl;

import app.ordering.food.common.Result;
import app.ordering.food.service.MinioService;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.IoUtil;
import io.minio.*;
import io.minio.messages.Item;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.util.*;

@Service("minioService")
public class MinioServiceImpl implements MinioService {

    @Resource
    private MinioClient minioClient;

    @Override
    public Result<List<Map<String, Object>>> list(String bucket) {
        // Make the bucket if it does not exist
        makeBucket(bucket);
        Iterable<io.minio.Result<Item>> objects = minioClient.
                listObjects(ListObjectsArgs.builder()
                        .bucket(bucket)
                        .recursive(true)
                        .build());
        List<Map<String, Object>> items = new ArrayList<>();
        try {
            for (io.minio.Result<Item> object : objects) {
                Item                item = object.get();
                Map<String, Object> args = new HashMap<>();
                args.put("lastModified", item.lastModified());
                args.put("size", item.size());
                args.put("name", item.objectName());
                args.put("directory", item.isDir());
                items.add(args);
            }
            return Result.success(items, "返回bucket内容成功");
        } catch (Exception e) {
            return Result.error("003M001", e.getMessage());
        }
    }

    @Override
    @SneakyThrows
    public void makeBucket(String bucket) {
        boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build());
        if (!found) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
        }
    }

    @Override
    public Optional<String> downloadBase64(String bucket, String filename) {
        Optional<String> file = Optional.empty();
        // Check if arg is null
        if (bucket == null || filename == null) {
            return file;
        }
        // Make the bucket if it does not exist
        makeBucket(bucket);
        // Check if the file exists
        if (!existObject(bucket, filename)) {
            return file;
        }
        InputStream inputStream = null;
        try {
            inputStream = minioClient.getObject(GetObjectArgs.builder().bucket(bucket).object(filename).build());
            file = Optional.of(Base64.encode(inputStream));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file;
    }

    @Override
    public ResponseEntity<byte[]> download(String bucket, String filename) {
        // Check if arg is null
        if (bucket == null || filename == null) {
            return new ResponseEntity<>(null, null, HttpStatus.BAD_REQUEST);
        }
        // Make the bucket if it does not exist
        makeBucket(bucket);
        // Check if the file exists
        if (!existObject(bucket, filename)) {
            return new ResponseEntity<>(null, null, HttpStatus.BAD_REQUEST);
        }
        InputStream            inputStream           = null;
        ByteArrayOutputStream  byteArrayOutputStream = null;
        try {
            inputStream = minioClient.getObject(GetObjectArgs.builder().bucket(bucket).object(filename).build());
            byteArrayOutputStream = new ByteArrayOutputStream();
            IoUtil.copy(inputStream, byteArrayOutputStream);
            byte[] bytes = byteArrayOutputStream.toByteArray();
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(bytes, httpHeaders, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return new ResponseEntity<>(null, null, HttpStatus.BAD_REQUEST);
    }

    @Override
    public boolean upload(String bucket, MultipartFile multipartFile, String filename) {
        if (bucket == null) {
            return false;
        }
        if (multipartFile == null) {
            return false;
        }
        if (filename == null) {
            return false;
        }
        // Make the bucket if it does not exist
        makeBucket(bucket);
        // Delete the old one if the file exists
        if (existObject(bucket, filename)) {
            removeObject(bucket, filename);
        }
        InputStream inputStream = null;
        try {
            inputStream = multipartFile.getInputStream();
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucket)
                    .object(filename)
                    .stream(inputStream, inputStream.available(), -1)
                    .contentType(multipartFile.getContentType())
                    .build());
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean existObject(String bucket, String filename) {
        if (bucket == null) {
            return false;
        }
        if (filename == null) {
            return false;
        }
        try {
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build());
            if (found) {
                minioClient.statObject(StatObjectArgs.builder().bucket(bucket).object(filename).build());
                return true;
            }
        } catch (Exception ignored) {

        }
        return false;
    }

    @Override
    public void removeObject(String bucket, String filename) {
        if (bucket != null && filename != null && existObject(bucket, filename)) {
            try {
                minioClient.removeObject(
                        RemoveObjectArgs.builder().bucket(bucket).object(filename).build());
            } catch (Exception ignored) {

            }
        }
    }

    @Override
    public void removeBucket(String bucket) {
        if (bucket != null) {
            try {
                boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build());
                if (found) {
                    // delete all files recursively in the bucket
                    Iterable<io.minio.Result<Item>> objects = minioClient.
                            listObjects(ListObjectsArgs.builder()
                                    .bucket(bucket)
                                    .recursive(true)
                                    .build());
                    objects.forEach(e -> {
                        try {
                            minioClient.removeObject(
                                    RemoveObjectArgs.builder().bucket(bucket).object(e.get().objectName()).build());
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    });
                    minioClient.removeBucket(RemoveBucketArgs.builder().bucket(bucket).build());
                }
            } catch (Exception ignored) {

            }
        }
    }
}
