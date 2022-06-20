package app.ordering.food.controller;

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
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "Admin Controller")
public class AdminController {

    @Resource
    private OrderDetailsRepository orderDetailsRepository;

    @Resource
    private OrderDeliveryMethodService orderDeliveryMethodService;

    @Resource
    private OrderStatusService orderStatusService;

    @Resource
    private CategoryService categoryService;

    @Resource
    private MerchantService merchantService;

    @Resource
    private ProductService productService;

    @Resource
    private MinioService minioService;

    @Resource
    private OrderService orderService;

    @Resource
    private ClientService clientService;

    @Value("${minio.bucketForMerchants}")
    private String bucketForMerchants;

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @ApiOperation("Get all orders")
    @GetMapping("api/v1/admin/order/all")
    public Result<List<Map<String, Object>>> getOrders() {
        List<Order>  orders  = orderService.list();
        if (orders == null) {
            return Result.error("", "获取order列表失败");
        }
        List<Map<String, Object>> result = new ArrayList<>();
        for (Order order : orders) {
            Map<String, Object> map = MapUtil.newHashMap();
            BeanUtil.copyProperties(order, map);
            OrderDetails orderDetails = orderDetailsRepository.findByOrderId(order.getId());
            if (orderDetails == null) {
                return Result.error("", "获取order列表失败");
            }
            map.put("products", orderDetails.getProducts());
            result.add(map);
        }
        return Result.success(result, "获取order列表成功");
    }

    @ApiOperation("Insert an empty order")
    @PostMapping("api/v1/admin/order/insertEmpty")
    public Result<Map<String, Object>> insertEmptyOrder() {
        Order order = new Order();
        List<Map<String, Object>> products = new ArrayList<>();
        if (!orderService.save(order)) {
            return Result.error("", "插入空order失败");
        }
        OrderDetails orderDetails = orderDetailsRepository.insert(new OrderDetails(order.getId(), products));
        Map<String, Object> map = MapUtil.newHashMap();
        BeanUtil.copyProperties(order, map);
        map.put("products", orderDetails.getProducts());
        return Result.success(map, "插入空order成功");
    }

    @ApiOperation("Update an order")
    @PostMapping("api/v1/admin/order/update")
    public Result<Map<String, Object>> updateOrder(@RequestBody @NotNull Map<String, Object> requestBody) {
        if (requestBody == null) {
            return Result.error("","参数体为null");
        }
        if (!requestBody.containsKey("id")) {
            return Result.error("", "参数体不包含id");
        }
        if (requestBody.get("id") == null) {
            return Result.error("", "id为null");
        }
        if (!(requestBody.get("id") instanceof Integer)) {
            return Result.error("", "id参数类型不匹配");
        }
        Integer  id = (Integer) requestBody.get("id");
        Order order = orderService.getById(id);
        if (order == null) {
            return Result.error("", "id不存在");
        }
        int length = 1;
        if (requestBody.containsKey("clientId")) {
            if (requestBody.get("clientId") == null) {
                return Result.error("", "clientId为null");
            }
            if (!(requestBody.get("clientId") instanceof Integer)) {
                return Result.error("", "clientId参数类型不匹配");
            }
            Integer clientId = (Integer) requestBody.get("clientId");
            Client client = clientService.getById(clientId);
            if (client == null) {
                return Result.error("", "client id不存在");
            }
            order.setClientId(clientId);
            ++length;
        }
        if (requestBody.containsKey("merchantId")) {
            if (requestBody.get("merchantId") == null) {
                return Result.error("", "merchantId为null");
            }
            if (!(requestBody.get("merchantId") instanceof Integer)) {
                return Result.error("", "merchantId参数类型不匹配");
            }
            Integer merchantId = (Integer) requestBody.get("merchantId");
            Merchant merchant = merchantService.getById(merchantId);
            if (merchant == null) {
                return Result.error("", "merchant id不存在");
            }
            order.setMerchantId(merchantId);
            ++length;
        }
        if (requestBody.containsKey("address")) {
            if (requestBody.get("address") == null) {
                return Result.error("", "address为null");
            }
            if (!(requestBody.get("address") instanceof String)) {
                return Result.error("", "address参数类型不匹配");
            }
            String address = (String)requestBody.get("address");
            order.setAddress(address);
            ++length;
        }
        if (requestBody.containsKey("phone")) {
            if (requestBody.get("phone") == null) {
                return Result.error("", "phone为null");
            }
            if (!(requestBody.get("phone") instanceof String)) {
                return Result.error("", "phone参数类型不匹配");
            }
            String phone = (String)requestBody.get("phone");
            order.setPhone(phone);
            ++length;
        }
        if (requestBody.containsKey("paymentMethod")) {
            if (requestBody.get("paymentMethod") == null) {
                return Result.error("", "paymentMethod为null");
            }
            if (!(requestBody.get("paymentMethod") instanceof Integer)) {
                return Result.error("", "paymentMethod参数类型不匹配");
            }
            Integer paymentMethod = (Integer)requestBody.get("paymentMethod");
            order.setPaymentMethod(paymentMethod);
            ++length;
        }
        if (requestBody.containsKey("deliveryTime")) {
            if (requestBody.get("deliveryTime") == null) {
                return Result.error("", "deliveryTime为null");
            }
            if (!(requestBody.get("deliveryTime") instanceof String)) {
                return Result.error("", "deliveryTime参数类型不匹配");
            }
            String deliveryTimeStr = (String)requestBody.get("deliveryTime");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime deliveryTime = LocalDateTime.parse(deliveryTimeStr, formatter);
            order.setDeliveryTime(deliveryTime);
            ++length;
        }
        if (requestBody.containsKey("shipmentTime")) {
            if (requestBody.get("shipmentTime") == null) {
                return Result.error("", "shipmentTime为null");
            }
            if (!(requestBody.get("shipmentTime") instanceof String)) {
                return Result.error("", "shipmentTime参数类型不匹配");
            }
            String shipmentTimeStr = (String)requestBody.get("shipmentTime");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime shipmentTime = LocalDateTime.parse(shipmentTimeStr, formatter);
            order.setShipmentTime(shipmentTime);
            ++length;
        }
        if (requestBody.containsKey("deliveryMethodId")) {
            if (requestBody.get("deliveryMethodId") == null) {
                return Result.error("", "deliveryMethodId为null");
            }
            if (!(requestBody.get("deliveryMethodId") instanceof Integer)) {
                return Result.error("", "deliveryMethodId参数类型不匹配");
            }
            Integer deliveryMethodId = (Integer)requestBody.get("deliveryMethodId");
            OrderDeliveryMethod orderDeliveryMethod = orderDeliveryMethodService.getById(deliveryMethodId);
            if (orderDeliveryMethod == null) {
                return Result.error("", "deliveryMethodId为null");
            }
            order.setDeliveryMethodId(deliveryMethodId);
            ++length;
        }
        if (requestBody.containsKey("totalPrice")) {
            if (requestBody.get("totalPrice") == null) {
                return Result.error("", "totalPrice为null");
            }
            if (!(requestBody.get("totalPrice") instanceof Integer)) {
                return Result.error("", "totalPrice参数类型不匹配");
            }
            Integer totalPrice = (Integer)requestBody.get("totalPrice");
            order.setTotalPrice(totalPrice);
            ++length;
        }
        if (requestBody.containsKey("deliveryFee")) {
            if (requestBody.get("deliveryFee") == null) {
                return Result.error("", "deliveryFee为null");
            }
            if (!(requestBody.get("deliveryFee") instanceof Integer)) {
                return Result.error("", "deliveryFee参数类型不匹配");
            }
            Integer deliveryFee = (Integer)requestBody.get("deliveryFee");
            order.setDeliveryFee(deliveryFee);
            ++length;
        }
        if (requestBody.containsKey("statusId")) {
            if (requestBody.get("statusId") == null) {
                return Result.error("", "statusId为null");
            }
            if (!(requestBody.get("statusId") instanceof Integer)) {
                return Result.error("", "statusId参数类型不匹配");
            }
            Integer statusId = (Integer)requestBody.get("statusId");
            OrderStatus orderStatus = orderStatusService.getById(statusId);
            if (orderStatus == null) {
                return Result.error("", "status id不存在");
            }
            order.setStatusId(statusId);
            ++length;
        }
        if (requestBody.containsKey("comment")) {
            if (requestBody.get("comment") == null) {
                return Result.error("", "comment为null");
            }
            if (!(requestBody.get("comment") instanceof String)) {
                return Result.error("", "comment参数类型不匹配");
            }
            String comment = (String)requestBody.get("comment");
            order.setComment(comment);
            ++length;
        }
        List<Map<String, Object>> productList = null;
        if (requestBody.containsKey("products")) {
            if (requestBody.get("products") == null) {
                return Result.error("", "products为null");
            }
            if (!(requestBody.get("products") instanceof List<?>)) {
                return Result.error("", "products参数类型不匹配");
            }
            List<?> products = (List<?>) requestBody.get("products");
            productList = new ArrayList<>();
            for (Object object : products) {
                productList.add((Map<String, Object>) object);
            }
            OrderDetails orderDetails = orderDetailsRepository.findByOrderId(id);
            orderDetails.setProducts(productList);
            // update 'orderDetails'
            orderDetailsRepository.save(orderDetails);
            ++length;
        }
        if (requestBody.size() > length) {
            return Result.error("", "参数体包含多余参数");
        }
        order.setUpdateAt(null);
        // update 'order'
        if (!(orderService.updateById(order))) {
            return Result.error("", "order更新失败");
        }
        Map<String, Object> result = MapUtil.newHashMap();
        BeanUtil.copyProperties(order, result);
        if (productList != null) {
            result.put("products", productList);
        }
        return Result.success(result, "order更新成功");
    }

    @ApiOperation("Get all categories")
    @GetMapping("api/v1/admin/category/all")
    public Result<List<Category>> getCategories() {
        List<Category> categories = categoryService.list();
        if (categories == null) {
            return Result.error("", "获取category列表失败");
        }
        return Result.success(categories, "获取category列表成功");
    }

    @ApiOperation("Get all categories of a merchant by the merchant ID")
    @PostMapping("api/v1/admin/category/merchant")
    public Result<List<Category>> getCategoriesByMerchantId(@RequestBody @NotNull Map<String, Object> requestBody) {
        if (requestBody == null) {
            return Result.error("","参数体为null");
        }
        if (!requestBody.containsKey("merchantId")) {
            return Result.error("", "参数体不包含merchant id");
        }
        if (requestBody.get("merchantId") == null) {
            return Result.error("", "merchant id为null");
        }
        if (!(requestBody.get("merchantId") instanceof Integer)) {
            return Result.error("", "merchant id参数类型不匹配");
        }
        if (requestBody.size() > 1) {
            return Result.error("", "参数体包含多余参数");
        }
        Integer  merchantId = (Integer) requestBody.get("merchantId");
        Merchant merchant   = merchantService.getById(merchantId);
        if (merchant == null) {
            return Result.error("", "merchant id不存在");
        }
        List<Category> categories = categoryService.listByMerchantId(merchantId);
        if (categories == null) {
            return Result.error("", "获取category列表失败");
        }
        return Result.success(categories, "获取category列表成功");
    }

    @ApiOperation("Get a category by the category ID")
    @PostMapping("api/v1/admin/category/id")
    public Result<Category> getCategoryById(@RequestBody @NotNull Map<String, Object> requestBody) {
        if (requestBody == null) {
            return Result.error("005P006","参数体为null");
        }
        if (!requestBody.containsKey("id")) {
            return Result.error("005P007", "参数体不包含id");
        }
        if (requestBody.get("id") == null) {
            return Result.error("005P008", "id为null");
        }
        if (!(requestBody.get("id") instanceof Integer)) {
            return Result.error("005P009", "id参数类型不匹配");
        }
        if (requestBody.size() > 1) {
            return Result.error("005P010", "参数体包含多余参数");
        }
        Integer id = (Integer) requestBody.get("id");
        Category category = categoryService.getById(id);
        if (category == null) {
            return Result.error("005B002", "id不存在");
        }
        return Result.success(category, "获取category成功");
    }

    @ApiOperation("Get all categories of a product by the product ID")
    @PostMapping("api/v1/admin/product/categories")
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
        if (!(requestBody.get("productId") instanceof Integer)) {
            return Result.error("005P014", "productId参数类型不匹配");
        }
        if (requestBody.size() > 1) {
            return Result.error("005P015", "参数体包含多余参数");
        }
        Integer productId = (Integer) requestBody.get("productId");
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
    @PostMapping("api/v1/admin/category/products")
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
        if (!(requestBody.get("productId") instanceof Integer)) {
            return Result.error("005P019", "id参数类型不匹配");
        }
        if (requestBody.size() > 1) {
            return Result.error("005P020", "参数体包含多余参数");
        }
        Integer categoryId = (Integer) requestBody.get("id");
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

    @ApiOperation("Get all merchants")
    @GetMapping("api/v1/admin/merchant/all")
    public Result<List<Merchant>> getMerchants() {
        List<Merchant> merchants = merchantService.list();
        if (merchants == null) {
            return Result.error("", "获取merchant列表失败");
        }
        return Result.success(merchants, "获取merchant列表成功");
    }

    @ApiOperation("Get a merchant without the merchant's image by the merchant ID")
    @PostMapping("api/v1/admin/merchant/id")
    public Result<Merchant> getMerchantById(@RequestBody @NotNull Map<String, Object> requestBody) {
        if (requestBody == null) {
            return Result.error("001P001", "参数体为null");
        }
        if (!requestBody.containsKey("id")) {
            return Result.error("001P002", "参数体不包含id");
        }
        if (requestBody.get("id") == null) {
            return Result.error("001P003", "id为null");
        }
        if (!(requestBody.get("id") instanceof Integer)) {
            return Result.error("001P004", "id参数类型不匹配");
        }
        if (requestBody.size() > 1) {
            return Result.error("001P005", "参数体包含多余参数");
        }
        Integer id = (Integer) requestBody.get("id");
        Merchant merchant = merchantService.getById(id);
        if (merchant == null) {
            return Result.error("001B001", "merchant不存在");
        }
        return Result.success(merchant, "merchant获取成功");
    }

    @ApiOperation("Get the image of a merchant by the merchant ID")
    @PostMapping("api/v1/admin/merchant/image")
    public ResponseEntity<byte[]> getMerchantImageById(@RequestBody @NotNull Map<String, Object> requestBody) {
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
        Integer id = (Integer) requestBody.get("id");
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

    @ApiOperation("Upload the image of a merchant by the merchant ID")
    @PostMapping("api/v1/admin/merchant/image/upload")
    public Result<Void> uploadMerchantImageById(
            @RequestParam("id") @NotNull Integer id,
            @RequestPart("file") @NotNull MultipartFile multipartFile
    ) {
        if (id == null) {
            return Result.error("001P006", "id为null");
        }
        if (multipartFile == null) {
            return Result.error("001P007", "multipartFile为null");
        }
        Merchant merchant = merchantService.getById(id);
        if (merchant == null) {
            return Result.error("001B002", "merchant不存在");
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
    @PostMapping("api/v1/admin/merchant/insert")
    public Result<Merchant> insertMerchant(@RequestBody @NotNull Map<String, Object> requestBody) {
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
    @PostMapping("api/v1/admin/merchant/update")
    public Result<Merchant> updateMerchantById(@RequestBody @NotNull Map<String, Object> requestBody) {
        if (requestBody == null) {
            return Result.error("001P043", "参数体为null");
        }
        int length = 0;
        if (!requestBody.containsKey("id")) {
            return Result.error("001P044", "参数体不包含id");
        }
        if (requestBody.get("id") == null) {
            return Result.error("001P045", "id为null");
        }
        if (!(requestBody.get("id") instanceof Integer)) {
            return Result.error("001P046", "id参数类型不匹配");
        }
        Integer id = (Integer) requestBody.get("id");
        Merchant merchant = merchantService.getById(id);
        if (merchant == null) {
            return Result.error("001B005", "获取merchant失败");
        } else {
            ++length;
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
}
