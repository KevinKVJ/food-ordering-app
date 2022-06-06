package app.ordering.food.service.impl;

import app.ordering.food.common.Result;
import app.ordering.food.entity.Product;
import app.ordering.food.mapper.ProductMapper;
import app.ordering.food.service.MinioService;
import app.ordering.food.service.ProductService;
import cn.hutool.core.io.IoUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

@Service("productService")
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Resource
    private MinioService minioService;

    @Value("${minio.bucketForProducts}")
    private String bucket;

    @Override
    public Result<List<Product>> getProducts() {
        List<Product> products = this.list();
        if (products == null) {
            return Result.error("002M001", "获取product列表失败");
        }
        return Result.success(products, "获取product列表成功");
    }

    @Override
    public Result<Product> getProductById(Map<String, Object> requestBody) {
        if (requestBody == null) {
            return Result.error("002P001", "参数体为null");
        }
        if (!requestBody.containsKey("id")) {
            return Result.error("002P002", "参数体不包含id");
        }
        if (requestBody.get("id") == null) {
            return Result.error("002P003", "id为null");
        }
        if (!(requestBody.get("id") instanceof Integer)) {
            return Result.error("002P004", "id参数类型不匹配");
        }
        if (requestBody.size() > 1) {
            return Result.error("002P005", "参数体包含多余参数");
        }
        Integer  id       = (Integer) requestBody.get("id");
        Product product = this.getById(id);
        if (product == null) {
            return Result.error("002B001", "product不存在");
        }
        return Result.success(product, "product获取成功");
    }

    @Override
    public ResponseEntity<byte[]> getImageById(Map<String, Object> requestBody) {
        if (requestBody == null) {
            return new ResponseEntity<>(null, null, HttpStatus.BAD_REQUEST);
        }
        if (!requestBody.containsKey("id")) {
            return new ResponseEntity<>(null, null, HttpStatus.BAD_REQUEST);
        }
        if (requestBody.get("id") == null) {
            return new ResponseEntity<>(null, null, HttpStatus.BAD_REQUEST);
        }
        if (!(requestBody.get("id") instanceof Integer)) {
            return new ResponseEntity<>(null, null, HttpStatus.BAD_REQUEST);
        }
        if (requestBody.size() > 1) {
            return new ResponseEntity<>(null, null, HttpStatus.BAD_REQUEST);
        }
        Integer  id       = (Integer) requestBody.get("id");
        Product product = this.getById(id);
        if (product == null) {
            return new ResponseEntity<>(null, null, HttpStatus.BAD_REQUEST);
        }
        String fileName = id + ".jpg";
        return minioService.download(bucket, fileName);
    }

    @Override
    public Result<Void> uploadImageById(Integer id, MultipartFile multipartFile) {
        if (id == null) {
            return Result.error("002P006", "id为null");
        }
        if (multipartFile == null) {
            return Result.error("002P007", "multipartFile为null");
        }
        Product product = this.getById(id);
        if (product == null) {
            return Result.error("002M002", "product不存在");
        }
        if (!"image/jpeg".equals(multipartFile.getContentType())) {
            return Result.error("002P008", "multipartFile不是image/jpeg");
        }
        String filename = id + ".jpg";
        if (!minioService.upload(bucket, multipartFile, filename)) {
            return Result.error("002M003", "multipartFile上传失败");
        }
        return Result.success("multipartFile上传成功");
    }

    @Override
    public Result<Product> insert(Map<String, Object> requestBody) {
        if (requestBody == null) {
            return Result.error("002P009", "参数体为null");
        }
        if (!requestBody.containsKey("name")) {
            return Result.error("002P010", "参数体不包含name");
        }
        if (requestBody.get("name") == null) {
            return Result.error("002P011", "name为null");
        }
        if (!(requestBody.get("name") instanceof String)) {
            return Result.error("002P012", "name参数类型不匹配");
        }

        if (!requestBody.containsKey("monthly")) {
            return Result.error("002P013", "参数体不包含monthly");
        }
        if (requestBody.get("monthly") == null) {
            return Result.error("002P014", "monthly为null");
        }
        if (!(requestBody.get("monthly") instanceof Integer)) {
            return Result.error("002P015", "monthly参数类型不匹配");
        }

        if (!requestBody.containsKey("inventory")) {
            return Result.error("002P016", "参数体不包含inventory");
        }
        if (requestBody.get("inventory") == null) {
            return Result.error("002P017", "inventory为null");
        }
        if (!(requestBody.get("inventory") instanceof Integer)) {
            return Result.error("002P018", "inventory参数类型不匹配");
        }

        if (!requestBody.containsKey("discount")) {
            return Result.error("002P019", "参数体不包含discount");
        }
        if (requestBody.get("discount") == null) {
            return Result.error("002P020", "discount为null");
        }
        if (!(requestBody.get("discount") instanceof Integer)) {
            return Result.error("002P021", "discount参数类型不匹配");
        }

        if (!requestBody.containsKey("price")) {
            return Result.error("002P022", "参数体不包含price");
        }
        if (requestBody.get("price") == null) {
            return Result.error("002P023", "price为null");
        }
        if (!(requestBody.get("price") instanceof Integer)) {
            return Result.error("002P024", "price参数类型不匹配");
        }

        if (requestBody.size() > 5) {
            return Result.error("002P025", "参数体包含多余参数");
        }

        String name = (String)requestBody.get("name");
        Integer monthly = (Integer)requestBody.get("monthly");
        Integer inventory = (Integer)requestBody.get("inventory");
        Integer discount = (Integer) requestBody.get("discount");
        Integer price = (Integer)requestBody.get("price");

        Product product = new Product();
        product.setName(name);
        product.setMonthly(monthly);
        product.setInventory(inventory);
        product.setDiscount(discount);
        product.setPrice(price);
        if (!this.save(product)) {
            return Result.error("002M003", "product插入失败");
        }
        File file;
        try {
            file = ResourceUtils.getFile("classpath:static/product.jpg");
        } catch (FileNotFoundException e) {
            return Result.error("002F001", e.getMessage());
        }
        String filename = product.getId() + ".jpg";
        String contentType = "image/jpeg";
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            return Result.error("002F002", e.getMessage());
        }
        MultipartFile multipartFile = new MockMultipartFile(
                "file",
                filename,
                contentType,
                IoUtil.readBytes(fileInputStream)
        );
        if (!minioService.upload(bucket, multipartFile, filename)) {
            return Result.error("002M004", "product插入失败");
        }
        return Result.success(product, "product插入成功");
    }

    @Override
    public Result<Product> updateById(Map<String, Object> requestBody) {
        if (requestBody == null) {
            return Result.error("002P026", "参数体为null");
        }
        int length = 0;
        if (!requestBody.containsKey("id")) {
            return Result.error("002P027", "参数体不包含id");
        }
        if (requestBody.get("id") == null) {
            return Result.error("002P028", "id为null");
        }
        if (!(requestBody.get("id") instanceof Integer)) {
            return Result.error("002P029", "id参数类型不匹配");
        }
        Integer id = (Integer) requestBody.get("id");
        Product product = this.getById(id);
        if (product == null) {
            return Result.error("002B002", "id不存在");
        }
        ++length;
        if (requestBody.containsKey("name")) {
            if (requestBody.get("name") == null) {
                return Result.error("002P030", "name为null");
            }
            if (!(requestBody.get("name") instanceof String)) {
                return Result.error("002P031", "name参数类型不匹配");
            }
            product.setName((String)requestBody.get("name"));
            ++length;
        }
        if (requestBody.containsKey("monthly")) {
            if (requestBody.get("monthly") == null) {
                return Result.error("002P032", "monthly为null");
            }
            if (!(requestBody.get("monthly") instanceof Integer)) {
                return Result.error("002P033", "monthly参数类型不匹配");
            }
            product.setMonthly((Integer) requestBody.get("monthly"));
            ++length;
        }
        if (requestBody.containsKey("inventory")) {
            if (requestBody.get("inventory") == null) {
                return Result.error("002P034", "inventory为null");
            }
            if (!(requestBody.get("inventory") instanceof Integer)) {
                return Result.error("002P035", "inventory参数类型不匹配");
            }
            product.setInventory((Integer) requestBody.get("inventory"));
            ++length;
        }
        if (requestBody.containsKey("discount")) {
            if (requestBody.get("discount") == null) {
                return Result.error("002P036", "discount为null");
            }
            if (!(requestBody.get("discount") instanceof Integer)) {
                return Result.error("002P037", "discount参数类型不匹配");
            }
            product.setDiscount((Integer) requestBody.get("discount"));
            ++length;
        }
        if (requestBody.containsKey("price")) {
            if (requestBody.get("price") == null) {
                return Result.error("002P038", "price为null");
            }
            if (!(requestBody.get("price") instanceof Integer)) {
                return Result.error("002P039", "price参数类型不匹配");
            }
            product.setPrice((Integer) requestBody.get("price"));
            ++length;
        }
        if (length < requestBody.size()) {
            return Result.error("002P040", "参数体包含多余参数");
        }
        product.setUpdateAt(null);
        if (!this.updateById(product)) {
            return Result.error("002P041", "product更新失败");
        }
        return Result.success(product, "product更新成功");
    }
}
