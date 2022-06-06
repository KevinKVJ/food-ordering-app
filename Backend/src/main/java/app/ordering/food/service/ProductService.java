package app.ordering.food.service;

import app.ordering.food.common.Result;
import app.ordering.food.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ProductService extends IService<Product> {
    Result<List<Product>> getProducts();
    Result<Product> getProductById(Map<String, Object> requestBody);
    ResponseEntity<byte[]> getImageById(Map<String, Object> requestBody);
    Result<Void> uploadImageById(Integer id, MultipartFile multipartFile);
    Result<Product> insert(Map<String, Object> requestBody);
    Result<Product> updateById(Map<String, Object> requestBody);
}
