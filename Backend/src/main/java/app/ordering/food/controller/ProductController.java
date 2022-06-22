package app.ordering.food.controller;

import app.ordering.food.common.Result;
import app.ordering.food.entity.Merchant;
import app.ordering.food.entity.Product;
import app.ordering.food.service.MerchantService;
import app.ordering.food.service.MinioService;
import app.ordering.food.service.ProductService;
import cn.hutool.core.io.IoUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "Product Controller")
public class ProductController {

    @Resource
    private MinioService minioService;

    @Resource
    private MerchantService merchantService;

    @Value("${minio.bucketForProducts}")
    private String bucket;

    @Resource
    private ProductService productService;

    @ApiOperation("Get all products")
    @GetMapping("api/v1/product/all")
    public Result<List<Product>> getProducts() {
        List<Product> products = productService.list();
        if (products == null) {
            return Result.error("002M001", "获取product列表失败");
        }
        return Result.success(products, "获取product列表成功");
    }

    @ApiOperation("Get the image of a product by the product ID")
    @PostMapping("api/v1/product/image")
    public ResponseEntity<byte[]> getImageById(@RequestBody @NotNull Map<String, Object> requestBody) {
        if (requestBody == null) {
            return new ResponseEntity<>(null, null, HttpStatus.BAD_REQUEST);
        }
        if (!requestBody.containsKey("id")) {
            return new ResponseEntity<>(null, null, HttpStatus.BAD_REQUEST);
        }
        if (requestBody.get("id") == null) {
            return new ResponseEntity<>(null, null, HttpStatus.BAD_REQUEST);
        }
        if (!(requestBody.get("id") instanceof String)) {
            return new ResponseEntity<>(null, null, HttpStatus.BAD_REQUEST);
        }
        if (requestBody.size() > 1) {
            return new ResponseEntity<>(null, null, HttpStatus.BAD_REQUEST);
        }
        String  id       = (String) requestBody.get("id");
        Product product = productService.getById(id);
        if (product == null) {
            return new ResponseEntity<>(null, null, HttpStatus.BAD_REQUEST);
        }
        String filename = id + ".jpg";
        byte[] bytes = minioService.download(bucket, filename);
        if (bytes == null) {
            return new ResponseEntity<>(null, null, HttpStatus.BAD_REQUEST);
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(bytes, httpHeaders, HttpStatus.OK);
    }

    @ApiOperation("Get a product without its image by the product ID")
    @PostMapping("api/v1/product/id")
    public Result<Product> getById(@RequestBody @NotNull Map<String, Object> requestBody) {
        if (requestBody == null) {
            return Result.error("002P001", "参数体为null");
        }
        if (!requestBody.containsKey("id")) {
            return Result.error("002P002", "参数体不包含id");
        }
        if (requestBody.get("id") == null) {
            return Result.error("002P003", "id为null");
        }
        if (!(requestBody.get("id") instanceof String)) {
            return Result.error("002P004", "id参数类型不匹配");
        }
        if (requestBody.size() > 1) {
            return Result.error("002P005", "参数体包含多余参数");
        }
        String  id       = (String) requestBody.get("id");
        Product product = productService.getById(id);
        if (product == null) {
            return Result.error("002B001", "product不存在");
        }
        return Result.success(product, "product获取成功");
    }

    @ApiOperation("Upload the image of a product by the product ID")
    @PostMapping("api/v1/product/image/upload")
    public Result<Void> uploadImageById(
            @RequestParam("id") @NotNull String id,
            @RequestPart("file") @NotNull MultipartFile multipartFile
    ) {
        if (id == null) {
            return Result.error("002P006", "id为null");
        }
        if (multipartFile == null) {
            return Result.error("002P007", "multipartFile为null");
        }
        Product product = productService.getById(id);
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

    @ApiOperation("Get the base64 image of a product by the product ID")
    @PostMapping("api/v1/product/image64")
    public Result<String> getImage64ById(@RequestBody @NotNull Map<String, Object> requestBody) {
        if (requestBody == null) {
            return Result.error("002P045", "参数体为null");
        }
        if (!requestBody.containsKey("id")) {
            return Result.error("002P046", "参数体不包含id");
        }
        if (requestBody.get("id") == null) {
            return Result.error("002P047", "id为null");
        }
        if (!(requestBody.get("id") instanceof String)) {
            return Result.error("002P048", "id参数类型不匹配");
        }
        if (requestBody.size() > 1) {
            return Result.error("002P049", "参数体包含多余参数");
        }
        String id      = (String) requestBody.get("id");
        Merchant merchant = merchantService.getById(id);
        if (merchant == null) {
            return Result.error("002B004", "merchant不存在");
        }
        String           filename = id + ".jpg";
        String image64  = minioService.downloadBase64(bucket,filename);
        if (image64 != null) {
            return Result.success(image64, "merchant图片获取成功");
        }
        return Result.error("002M006", "merchant图片获取失败");
    }

    @ApiOperation("Insert a product")
    @PostMapping("api/v1/product/insert")
    public Result<Product> insert(@RequestBody @NotNull Map<String, Object> requestBody) {
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

        if (!requestBody.containsKey("merchantId")) {
            return Result.error("002P042", "参数体不包含merchantId");
        }
        if (requestBody.get("merchantId") == null) {
            return Result.error("002P043", "merchantId为null");
        }
        if (!(requestBody.get("merchantId") instanceof String)) {
            return Result.error("002P044", "merchantId参数类型不匹配");
        }

        if (requestBody.size() > 6) {
            return Result.error("002P025", "参数体包含多余参数");
        }

        String  merchantId = (String) requestBody.get("merchantId");
        Merchant merchant   = merchantService.getById(merchantId);
        if (merchant == null) {
            return Result.error("002B003", "merchant id不存在");
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
        product.setMerchantId(merchantId);

        if (!productService.save(product)) {
            return Result.error("002M004", "product插入失败");
        }
        File file;
        try {
            file = ResourceUtils.getFile("classpath:static/product.jpg");
        } catch (FileNotFoundException e) {
            return Result.error("002F001", e.getMessage());
        }
        String filename = product.getId() + ".jpg";
        String          contentType = "image/jpeg";
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
            return Result.error("002M005", "product插入失败");
        }
        return Result.success(product, "product插入成功");
    }

    @ApiOperation("Update a product")
    @PostMapping("api/v1/product/update")
    public Result<Product> updateById(@RequestBody @NotNull Map<String, Object> requestBody) {
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
        if (!(requestBody.get("id") instanceof String)) {
            return Result.error("002P029", "id参数类型不匹配");
        }
        String id = (String) requestBody.get("id");
        Product product = productService.getById(id);
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
        if (!productService.updateById(product)) {
            return Result.error("002P041", "product更新失败");
        }
        return Result.success(product, "product更新成功");
    }
}
