package app.ordering.food;

import app.ordering.food.common.Result;
import app.ordering.food.service.MinioService;
import cn.hutool.core.io.IoUtil;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Component
public class DataInitializer implements ApplicationRunner {

    @Resource
    private MinioService minioService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        File file;
        FileInputStream fileInputStream;
        String filename;
        MultipartFile multipartFile;
        String bucket;
        try {
            file = ResourceUtils.getFile("classpath:static/product.jpg");
            bucket = "food-ordering-app-products";
            for (int i = 1; i <= 2; ++i) {
                filename = i + ".jpg";
                try {
                    fileInputStream = new FileInputStream(file);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    return;
                }
                multipartFile = new MockMultipartFile(
                        "file",
                        filename,
                        "image/jpeg",
                        IoUtil.readBytes(fileInputStream)
                );
                minioService.upload(bucket, multipartFile, filename);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        try {
            file = ResourceUtils.getFile("classpath:static/merchant.jpg");
            bucket = "food-ordering-app-merchants";
            for (int i = 1; i <= 3; ++i) {
                filename = i + ".jpg";
                try {
                    fileInputStream = new FileInputStream(file);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    return;
                }
                multipartFile = new MockMultipartFile(
                        "file",
                        filename,
                        "image/jpeg",
                        IoUtil.readBytes(fileInputStream)
                );
                minioService.upload(bucket, multipartFile, filename);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
