package app.ordering.food.controller;

import app.ordering.food.common.JwtUtils;
import app.ordering.food.common.Result;
import app.ordering.food.entity.*;
import app.ordering.food.repository.OrderDetailsRepository;
import app.ordering.food.service.*;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "Merchant Controller")
public class MerchantController {

    @Resource
    private OrderService orderService;

    @Resource
    private OrderDetailsRepository orderDetailsRepository;

    @Resource
    private MerchantService merchantService;

    @Resource
    private MinioService minioService;

    @Resource
    private RedisService redisService;

    @Resource
    private CategoryService categoryService;

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Resource
    private ProductToCategoryService productToCategoryService;

    @Resource
    private ProductService productService;

    @Value("${minio.bucketForMerchants}")
    private String bucketForMerchants;

    @Value("${spring.redis.jwtTimeout}")
    private long timeout;

    @ApiOperation("Get all orders of the merchant")
    @GetMapping("api/v1/merchant/order/all")
    public Result<List<Map<String, Object>>> getOrders(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        String merchantId = redisService.getString(token);
        Merchant merchant   = merchantService.getById(merchantId);
        if (merchant == null) {
            return Result.error("", "merchant id不存在");
        }
        List<Map<String, Object>> orders = orderService.getOrdersOfMerchant(merchantId);
        if (orders == null) {
            return Result.error("", "merchant获取order失败");
        }
        for (Map<String, Object> order : orders) {
            if (!order.containsKey("id") || order.get("id") == null || !(order.get("id") instanceof String)) {
                return Result.error("", "获取order列表失败");
            }
            String id = (String)order.get("id");
            OrderDetails orderDetails = orderDetailsRepository.findByOrderId(id);
            if (orderDetails == null) {
                return Result.error("", "获取order列表失败");
            }
            order.put("products", orderDetails.getProducts());
        }
        return Result.success(orders, "merchant获取order成功");
    }

    @ApiOperation("Update the category")
    @PostMapping("api/v1/merchant/category/update")
    public Result<Category> update(@RequestBody @NotNull Map<String, Object> requestBody) {
        if (requestBody == null) {
            return Result.error("","参数体为null");
        }
        int length = 0;
        if (!requestBody.containsKey("id")) {
            return Result.error("", "参数体不包含category id");
        }
        if (requestBody.get("id") == null) {
            return Result.error("", "category id为null");
        }
        if (!(requestBody.get("id") instanceof String)) {
            return Result.error("", "category id参数类型不匹配");
        }
        ++length;
        String id = (String) requestBody.get("id");
        Category category = categoryService.getById(id);
        if (category == null) {
            return Result.error("", "category id不存在");
        }
        if (requestBody.containsKey("name")) {
            if (requestBody.get("name") == null) {
                return Result.error("", "name为null");
            }
            if (!(requestBody.get("name") instanceof String)) {
                return Result.error("", "name参数类型不匹配");
            }
            String name = (String)requestBody.get("name");
            category.setName(name);
            ++length;
        }
        String newMerchantId = null;
        if (requestBody.containsKey("merchantId")) {
            if (requestBody.get("merchantId") == null) {
                return Result.error("", "merchant id为null");
            }
            if (!(requestBody.get("merchantId") instanceof String)) {
                return Result.error("", "merchant id参数类型不匹配");
            }
            newMerchantId = (String) requestBody.get("merchantId");
            Merchant merchant = merchantService.getById(newMerchantId);
            if (merchant == null) {
                return Result.error("", "merchant id不存在");
            }
            ++length;
        }
        if (length < requestBody.size()) {
            return Result.error("", "参数体包含多余参数");
        }
        String merchantId = category.getMerchantId();
        // If the merchant ID is changed, remove all product IDs that belong to the category.
        if (newMerchantId != null && !newMerchantId.equals(merchantId)) {
            productToCategoryService.remove(new QueryWrapper<ProductToCategory>().eq("category_id", id));
        }
        category.setUpdateAt(null);
        if (!categoryService.save(category)) {
            return Result.error("", "category更新失败");
        }
        return Result.success(category, "category更新成功");
    }

    @ApiOperation("Insert batch categories")
    @PostMapping("api/v1/merchant/category/insertbatch")
    public Result<List<Category>> insertBatch(HttpServletRequest request,
                                              @RequestBody @NotNull Map<String, Object> requestBody) {
        String token = request.getHeader("Authorization").substring(7);
        String id =  redisService.getString(token);
        Merchant merchant   = merchantService.getById(id);
        if (merchant == null) {
            return Result.error("", "merchant id不存在");
        }
        if (requestBody == null) {
            return Result.error("","参数体为null");
        }
        if (!requestBody.containsKey("names")) {
            return Result.error("", "参数体不包含names");
        }
        if (requestBody.get("names") == null) {
            return Result.error("", "names为null");
        }
        if (!(requestBody.get("names") instanceof List<?>)) {
            return Result.error("", "names参数类型不匹配");
        }
        if (requestBody.size() > 1) {
            return Result.error("", "参数体包含多余参数");
        }
        List<?> names = (List<?>) requestBody.get("names");
        List<Category> categories = new ArrayList<>();
        for (Object name : names) {
            if (name == null) {
                return Result.error("", "names列表包含为null的元素");
            }
            if (!(name instanceof String)) {
                return Result.error("", "names列表包含类型不匹配的元素");
            }
            Category category = new Category();
            category.setName((String)name);
            category.setMerchantId(id);
            categories.add(category);
        }
        if (!categoryService.saveBatch(categories)) {
            return Result.error("", "category批量插入失败");
        }
        return Result.success(categories, "category批量插入成功");
    }

    @ApiOperation("Get all categories of the merchant")
    @GetMapping("api/v1/merchant/category")
    public Result<List<Category>> getCategoriesByMerchantId(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        String id =  redisService.getString(token);
        Merchant merchant   = merchantService.getById(id);
        if (merchant == null) {
            return Result.error("", "merchant id不存在");
        }
        List<Category> categories = categoryService.listByMerchantId(id);
        if (categories == null) {
            return Result.error("", "获取category列表失败");
        }
        return Result.success(categories, "获取category列表成功");
    }

    @ApiOperation("Get the merchant without the merchant's image")
    @PostMapping("api/v1/merchant")
    public Result<Merchant> getById(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        String id =  redisService.getString(token);
        Merchant merchant = merchantService.getById(id);
        if (merchant == null) {
            return Result.error("", "merchant不存在");
        }
        return Result.success(merchant, "merchant获取成功");
    }

    @ApiOperation("Get the image of the merchant itself")
    @GetMapping("api/v1/merchant/image")
    public ResponseEntity<byte[]> getImageById(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        String id =  redisService.getString(token);
        Merchant merchant = merchantService.getById(id);
        if (merchant == null) {
            return new ResponseEntity<>(null, null, HttpStatus.BAD_REQUEST);
        }
        String filename = id + ".jpg";
        byte[] bytes = minioService.download(bucketForMerchants, filename);
        if (bytes == null) {
            return new ResponseEntity<>(null, null, HttpStatus.BAD_REQUEST);
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(bytes, httpHeaders, HttpStatus.OK);
    }

    @ApiOperation("Merchant Login")
    @PostMapping("api/v1/merchant/login")
    public Result<String> login(@RequestBody @NotNull Map<String, Object> requestBody) {
        if (requestBody == null) {
            return Result.error("001P009", "参数体为空");
        }
        if (!requestBody.containsKey("phone")) {
            return Result.error("001P010", "参数体不包含phone");
        }
        if (requestBody.get("phone") == null) {
            return Result.error("001P011", "phone为null");
        }
        if (!(requestBody.get("phone") instanceof String)) {
            return Result.error("001P012", "phone参数类型不匹配");
        }
        if (!requestBody.containsKey("password")) {
            return Result.error("001P013", "参数体不包含password");
        }
        if (requestBody.get("password") == null) {
            return Result.error("001P014", "password为null");
        }
        if (!(requestBody.get("password") instanceof String)) {
            return Result.error("001P015", "password参数类型不匹配");
        }
        String phone = (String)requestBody.get("phone");
        String password = (String)requestBody.get("password");
        if (requestBody.size() > 2) {
            return Result.error("001P016", "参数体包含多余参数");
        }
        try {
            Merchant merchant = merchantService.getByPhone(phone);
            if (merchant == null) {
                return Result.error("001B003", "账号不存在 登录失败");
            }
            if (!bCryptPasswordEncoder.matches(password, merchant.getPassword())) {
                return Result.error("001B009", "密码不匹配 登录失败");
            }
            // Generate JWT
            String token = JwtUtils.createToken(merchant);
            if (token == null) {
                return Result.error("001B012", "token生成失败");
            }
            // Check if JWT is in redis, if not, cache it with an expiry time
            if (redisService.getString(token) != null) {
                return Result.error("001B010", "用户已经登录");
            } else {
                redisService.updateStringWithTtl(token, merchant.getId().toString(), timeout);
                return Result.success(token, "用户成功登录 token已保存");
            }
        } catch (Exception e) {
            return Result.error("001O001", e.getMessage());
        }
    }

    @ApiOperation("Merchant logout")
    @PostMapping("api/v1/merchant/logout")
    public Result<Void> logout(@RequestBody @NotNull Map<String, Object> requestBody) {
        if (requestBody == null) {
            return Result.error("001P069", "参数体为空");
        }
        if (!requestBody.containsKey("token")) {
            return Result.error("001P070", "参数体不包含token");
        }
        if (requestBody.get("token") == null) {
            return Result.error("001P071", "token为null");
        }
        if (!(requestBody.get("token") instanceof String)) {
            return Result.error("001P072", "token参数类型不匹配");
        }
        if (requestBody.size() > 1) {
            return Result.error("001P073", "参数体包含多余参数");
        }
        String token = (String)requestBody.get("token");
        if (redisService.getString(token) == null) {
            return Result.error("001B011", "用户未登录或token已经过期");
        }
        if (!redisService.delete(token)) {
            return Result.error("001M006", "token删除失败");
        }
        return Result.success("token删除成功");
    }

    @ApiOperation("Get all categories of a product by the product ID")
    @PostMapping("api/v1/merchant/product/categories")
    public Result<List<Category>> getCategoriesByProductId(@RequestBody @NotNull Map<String, Object> requestBody) {
        if (requestBody == null) {
            return Result.error("005P011","参数体为null");
        }
        if (!requestBody.containsKey("productId")) {
            return Result.error("005P012", "参数体不包含productId");
        }
        if (requestBody.get("productId") == null) {
            return Result.error("005P013", "productId为null");
        }
        if (!(requestBody.get("productId") instanceof String)) {
            return Result.error("005P014", "productId参数类型不匹配");
        }
        if (requestBody.size() > 1) {
            return Result.error("005P015", "参数体包含多余参数");
        }
        String productId = (String) requestBody.get("productId");
        Product product   = productService.getById(productId);
        if (product == null) {
            return Result.error("005B003", "product id不存在");
        }
        List<Category> categories = categoryService.getCategoriesByProductId(productId);
        if (categories == null) {
            return Result.error("005M003", "获取category列表失败");
        }
        return Result.success(categories, "获取category成功");
    }

    @ApiOperation("Get all products of a category by the category ID")
    @PostMapping("api/v1/merchant/category/products")
    public Result<List<Product>> getProductsByCategoryId(@RequestBody @NotNull Map<String, Object> requestBody) {
        if (requestBody == null) {
            return Result.error("005P016","参数体为null");
        }
        if (!requestBody.containsKey("id")) {
            return Result.error("005P017", "参数体不包含id");
        }
        if (requestBody.get("productId") == null) {
            return Result.error("005P018", "id为null");
        }
        if (!(requestBody.get("productId") instanceof String)) {
            return Result.error("005P019", "id参数类型不匹配");
        }
        if (requestBody.size() > 1) {
            return Result.error("005P020", "参数体包含多余参数");
        }
        String categoryId = (String) requestBody.get("id");
        Category category = categoryService.getById(categoryId);
        if (category == null) {
            return Result.error("005M004", "category id不存在");
        }
        List<Product> products = categoryService.getProductsByCategoryId(categoryId);
        if (products == null) {
            return Result.error("005M005", "获取product列表失败");
        }
        return Result.success(products, "获取product列表成功");
    }

    @ApiOperation("Upload the image of a merchant by the merchant ID")
    @PostMapping("api/v1/merchant/image/upload")
    public Result<Void> uploadImage(
            HttpServletRequest request,
            @RequestPart("file") @NotNull MultipartFile multipartFile
    ) {
        String token = request.getHeader("Authorization").substring(7);
        String id =  redisService.getString(token);
        Merchant merchant   = merchantService.getById(id);
        if (merchant == null) {
            return Result.error("", "merchant id不存在");
        }
        if (multipartFile == null) {
            return Result.error("001P007", "multipartFile为null");
        }
        if (!"image/jpeg".equals(multipartFile.getContentType())) {
            return Result.error("001P008", "multipartFile不是image/jpeg");
        }
        String filename = id + ".jpg";
        if (!minioService.upload(bucketForMerchants, multipartFile, filename)) {
            return Result.error("001M004", "multipartFile上传失败");
        }
        return Result.success("multipartFile上传成功");
    }

    @ApiOperation("Insert a merchant except the merchant's image")
    @PostMapping("api/v1/merchant/insert")
    public Result<Merchant> insert(@RequestBody @NotNull Map<String, Object> requestBody) {
        if (requestBody == null) {
            return Result.error("001P017", "参数体为null");
        }
        if (!requestBody.containsKey("phone")) {
            return Result.error("001P018", "参数体不包含phone");
        }
        if (requestBody.get("phone") == null) {
            return Result.error("001P019", "phone为null");
        }
        if (!(requestBody.get("phone") instanceof String)) {
            return Result.error("001P020", "phone参数类型不匹配");
        }

        String phone = (String)requestBody.get("phone");
        Merchant temp = merchantService.getOne(new QueryWrapper<Merchant>().eq("phone", phone));
        if (temp != null) {
            return Result.error("001B004", "phone已经存在");
        }

        if (!requestBody.containsKey("password")) {
            return Result.error("001P021", "参数体不包含password");
        }
        if (requestBody.get("password") == null) {
            return Result.error("001P022", "password为null");
        }
        if (!(requestBody.get("password") instanceof String)) {
            return Result.error("001P023", "password参数类型不匹配");
        }

        if (!requestBody.containsKey("name")) {
            return Result.error("001P024", "参数体不包含name");
        }
        if (requestBody.get("name") == null) {
            return Result.error("001P025", "name为null");
        }
        if (!(requestBody.get("name") instanceof String)) {
            return Result.error("001P026", "name参数类型不匹配");
        }

        if (!requestBody.containsKey("description")) {
            return Result.error("001P027", "参数体不包含description");
        }
        if (requestBody.get("description") == null) {
            return Result.error("001P028", "description为null");
        }
        if (!(requestBody.get("description") instanceof String)) {
            return Result.error("001P029", "description参数类型不匹配");
        }

        if (!requestBody.containsKey("address")) {
            return Result.error("001P030", "参数体不包含address");
        }
        if (requestBody.get("address") == null) {
            return Result.error("001P031", "address为null");
        }
        if (!(requestBody.get("address") instanceof String)) {
            return Result.error("001P032", "address参数类型不匹配");
        }

        if (!requestBody.containsKey("publicPhone")) {
            return Result.error("001P033", "参数体不包含publicPhone");
        }
        if (requestBody.get("publicPhone") == null) {
            return Result.error("001P034", "publicPhone为null");
        }
        if (!(requestBody.get("publicPhone") instanceof String)) {
            return Result.error("001P035", "publicPhone参数类型不匹配");
        }

        if (!requestBody.containsKey("publicAddress")) {
            return Result.error("001P036", "参数体不包含publicAddress");
        }
        if (requestBody.get("publicAddress") == null) {
            return Result.error("001P037", "publicAddress为null");
        }
        if (!(requestBody.get("publicAddress") instanceof String)) {
            return Result.error("001P038", "publicAddress参数类型不匹配");
        }

        if (!requestBody.containsKey("businessHours")) {
            return Result.error("001P039", "参数体不包含businessHours");
        }
        if (requestBody.get("businessHours") == null) {
            return Result.error("001P040", "businessHours为null");
        }
        if (!(requestBody.get("businessHours") instanceof String)) {
            return Result.error("001P041", "businessHours参数类型不匹配");
        }

        if (requestBody.size() > 8) {
            return Result.error("001P042", "参数体包含多余参数");
        }

        String password = (String)requestBody.get("password");
        String name = (String)requestBody.get("name");
        String description = (String)requestBody.get("description");
        String address = (String)requestBody.get("address");
        String publicPhone = (String)requestBody.get("publicPhone");
        String publicAddress = (String)requestBody.get("publicAddress");
        String businessHours = (String)requestBody.get("businessHours");

        Merchant merchant = new Merchant();
        merchant.setPhone(phone);
        merchant.setPassword(bCryptPasswordEncoder.encode(password));
        merchant.setName(name);
        merchant.setDescription(description);
        merchant.setAddress(address);
        merchant.setPublicPhone(publicPhone);
        merchant.setPublicAddress(publicAddress);
        merchant.setBusinessHours(businessHours);

        if (!merchantService.save(merchant)) {
            return Result.error("001M002", "merchant插入失败");
        }

        File file;
        try {
            file = ResourceUtils.getFile("classpath:static/merchant.jpg");
        } catch (FileNotFoundException e) {
            return Result.error("001F001", e.getMessage());
        }
        String filename = merchant.getId() + ".jpg";
        String          contentType = "image/jpeg";
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            return Result.error("001F002", e.getMessage());
        }
        MultipartFile multipartFile = new MockMultipartFile(
                "file",
                filename,
                contentType,
                IoUtil.readBytes(fileInputStream)
        );
        if (!minioService.upload(bucketForMerchants, multipartFile, filename)) {
            return Result.error("001M003", "merchant插入失败");
        }
        return Result.success(merchant, "merchant插入成功");
    }

    @ApiOperation("Update a merchant except the merchant's image")
    @PostMapping("api/v1/merchant/update")
    public Result<Merchant> update(
            HttpServletRequest request,
            @RequestBody @NotNull Map<String, Object> requestBody) {
        String token = request.getHeader("Authorization").substring(7);
        String id = redisService.getString(token);
        Merchant merchant   = merchantService.getById(id);
        if (merchant == null) {
            return Result.error("", "merchant id不存在");
        }
        if (requestBody == null) {
            return Result.error("001P043", "参数体为null");
        }
        int length = 0;
        if (requestBody.containsKey("phone")) {
            if (requestBody.get("phone") == null) {
                return Result.error("001P047", "phone为null");
            }
            if (!(requestBody.get("phone") instanceof String)) {
                return Result.error("001P048", "phone参数类型不匹配");
            }
            String newPhone = (String)requestBody.get("phone");
            if (!newPhone.equals(merchant.getPhone())) {
                Merchant temp2 = merchantService.getOne(new QueryWrapper<Merchant>()
                        .eq("phone", newPhone));
                if (temp2 != null) {
                    return Result.error("001B006", "phone已经存在");
                }
            }
            merchant.setPhone((String)requestBody.get("phone"));
            ++length;
        }
        if (requestBody.containsKey("password")) {
            if (requestBody.get("password") == null) {
                return Result.error("001P049", "password为null");
            }
            if (!(requestBody.get("password") instanceof String)) {
                return Result.error("001P050", "password参数类型不匹配");
            }
            merchant.setPassword(bCryptPasswordEncoder.encode((String)requestBody.get("password")));
            ++length;
        }
        if (requestBody.containsKey("name")) {
            if (requestBody.get("name") == null) {
                return Result.error("001P051", "name为null");
            }
            if (!(requestBody.get("name") instanceof String)) {
                return Result.error("001P052", "name参数类型不匹配");
            }
            merchant.setName((String)requestBody.get("name"));
            ++length;
        }
        if (requestBody.containsKey("description")) {
            if (requestBody.get("description") == null) {
                return Result.error("001P053", "description为null");
            }
            if (!(requestBody.get("description") instanceof String)) {
                return Result.error("001P054", "description参数类型不匹配");
            }
            merchant.setDescription((String)requestBody.get("description"));
            ++length;
        }
        if (requestBody.containsKey("address")) {
            if (requestBody.get("address") == null) {
                return Result.error("001P055", "address为null");
            }
            if (!(requestBody.get("address") instanceof String)) {
                return Result.error("001P056", "address参数类型不匹配");
            }
            merchant.setAddress((String)requestBody.get("address"));
            ++length;
        }
        if (requestBody.containsKey("publicPhone")) {
            if (requestBody.get("publicPhone") == null) {
                return Result.error("001P057", "publicPhone为null");
            }
            if (!(requestBody.get("publicPhone") instanceof String)) {
                return Result.error("001P058", "publicPhone参数类型不匹配");
            }
            merchant.setPublicPhone((String)requestBody.get("publicPhone"));
            ++length;
        }
        if (requestBody.containsKey("publicAddress")) {
            if (requestBody.get("publicAddress") == null) {
                return Result.error("001P059", "publicAddress为null");
            }
            if (!(requestBody.get("publicAddress") instanceof String)) {
                return Result.error("001P060", "publicAddress参数类型不匹配");
            }
            merchant.setPublicAddress((String)requestBody.get("publicAddress"));
            ++length;
        }
        if (requestBody.containsKey("businessHours")) {
            if (requestBody.get("businessHours") == null) {
                return Result.error("001P061", "businessHours为null");
            }
            if (!(requestBody.get("businessHours") instanceof String)) {
                return Result.error("001P062", "businessHours参数类型不匹配");
            }
            merchant.setBusinessHours((String)requestBody.get("businessHours"));
            ++length;
        }
        if (requestBody.size() > length) {
            return Result.error("001P063", "参数体包含多余参数");
        }
        merchant.setUpdateAt(null);
        if (!merchantService.updateById(merchant)) {
            return Result.error("001B007", "merchant更新失败");
        }
        return Result.success(merchant, "merchant更新成功");
    }
}
