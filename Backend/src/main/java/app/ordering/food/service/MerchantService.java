package app.ordering.food.service;

import app.ordering.food.common.Result;
import app.ordering.food.entity.Merchant;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface MerchantService extends IService<Merchant> {
    Result<List<Merchant>> getMerchants();
    Result<Merchant> getMerchantById(Map<String, Object> requestBody);
    ResponseEntity<byte[]> getImageById(Map<String, Object> requestBody);
    Result<Void> uploadImageById(Integer id, MultipartFile multipartFile);
    Result<String> login(Map<String, Object> requestBody);
    Result<Merchant> insert(Map<String, Object> requestBody);
    Result<Merchant> updateById(Map<String, Object> requestBody);
}
