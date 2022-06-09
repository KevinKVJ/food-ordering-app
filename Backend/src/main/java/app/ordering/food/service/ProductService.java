package app.ordering.food.service;

import app.ordering.food.common.Result;
import app.ordering.food.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface ProductService extends IService<Product> {
}
