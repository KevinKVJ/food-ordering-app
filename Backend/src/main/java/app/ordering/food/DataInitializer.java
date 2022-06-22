package app.ordering.food;

import app.ordering.food.entity.*;
import app.ordering.food.service.*;
import cn.hutool.core.io.IoUtil;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Resource
    private RedisService redisService;

    @Resource
    private ProductService productService;

    @Resource
    private MerchantService merchantService;

    @Resource
    private CategoryService categoryService;

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Resource
    private ClientService clientService;

    @Resource
    private OrderStatusService orderStatusService;

    @Resource
    private OrderDeliveryMethodService orderDeliveryMethodService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // initialize tables in mysql db
        // t_merchant
        for (int i = 1; i <= 3; ++i) {
            Merchant merchant = new Merchant();
            merchant.setPhone("100000000" + i);
            merchant.setPassword(bCryptPasswordEncoder.encode("password" + i));
            merchant.setName("name" + i);
            merchant.setDescription("description" + i);
            merchant.setAddress("address" + i);
            merchant.setPublicPhone("publicPhone" + i);
            merchant.setPublicAddress("publicAddress" + i);
            merchant.setBusinessHours("businessHours" + i);
            merchantService.save(merchant);
        }
        // t_order
        for (int i = 1; i <= 3; ++i) {
            Product product = new Product();
            product.setName("product" + i);
            product.setMonthly(i*10+5);
            product.setInventory(i*1000);
            product.setDiscount(0);
            product.setPrice(i*1000+500);
            product.setMerchantId(String.valueOf(i));
            productService.save(product);
        }
        // t_client
        for (int i = 1; i <= 3; ++i) {
            Client client = new Client();
            client.setPhone("200000000" + i);
            client.setPassword(bCryptPasswordEncoder.encode("password" + i));
            client.setName("client" + i);
            clientService.save(client);
        }
        // t_category
        for (int i = 1; i <= 3; ++i) {
            for (int j = 1; j <= 3; ++j) {
                Category category = new Category();
                category.setName("category_" + i + "_" + j);
                category.setMerchantId(String.valueOf(j));
                categoryService.save(category);
            }
        }
        // t_order_status
        String[] statusList = new String[]{
                "待支付",
                "已支付",
                "已完成",
                "未支付取消",
                "已支付取消",
                "退款处理中",
                "退款成功",
                "退款完成",
                "退款失败"
        };
        for (String status : statusList) {
            OrderStatus orderStatus = new OrderStatus();
            orderStatus.setDescription(status);
            orderStatusService.save(orderStatus);
        }
        // t_order_delivery_method
        String[] deliveryMethodList = new String[]{
                "自提",
                "骑手配送"
        };
        for (String deliveryMethod : deliveryMethodList) {
            OrderDeliveryMethod orderDeliveryMethod = new OrderDeliveryMethod();
            orderDeliveryMethod.setDescription(deliveryMethod);
            orderDeliveryMethodService.save(orderDeliveryMethod);
        }


        // clear redis cache
        redisService.flushDb();
        // add default images for default products and default merchants in minio
        File file;
        FileInputStream fileInputStream;
        String filename;
        MultipartFile multipartFile;
        String bucket;
        try {
            file = ResourceUtils.getFile("classpath:static/product.jpg");
            bucket = "food-ordering-app-products";
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
