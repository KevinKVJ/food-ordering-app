package app.ordering.food.controller;

import app.ordering.food.common.Result;
import app.ordering.food.entity.Merchant;
import app.ordering.food.service.MerchantService;
import app.ordering.food.service.MinioService;
import cn.hutool.core.io.IoUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
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
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/merchant")
@Api(tags = "Merchant Controller")
public class MerchantController {

    @Resource
    private MerchantService merchantService;

    @Resource
    private MinioService minioService;

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${minio.bucketForMerchants}")
    private String bucket;

    @ApiOperation("Get all merchants")
    @GetMapping("/all")
    public Result<List<Merchant>> list() {
        List<Merchant> merchants = merchantService.list();
        if (merchants == null) {
            return Result.error("001M001", "获取merchant列表失败");
        }
        return Result.success(merchants, "获取merchant列表成功");
    }

    @ApiOperation("Get a merchant without its image by its ID")
    @PostMapping("/id")
    public Result<Merchant> getById(@RequestBody @NotNull Map<String, Object> requestBody) {
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

    @ApiOperation("Get the image of a merchant by its ID")
    @PostMapping("/image")
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
        String fileName = id + ".jpg";
        return minioService.download(bucket, fileName);
    }

    @ApiOperation("Get the base64 image of a merchant by its ID")
    @PostMapping("/image64")
    public Result<String> getImage64ById(@RequestBody @NotNull Map<String, Object> requestBody) {
        if (requestBody == null) {
            return Result.error("001P064", "参数体为null");
        }
        if (!requestBody.containsKey("id")) {
            return Result.error("001P065", "参数体不包含id");
        }
        if (requestBody.get("id") == null) {
            return Result.error("001P066", "id为null");
        }
        if (!(requestBody.get("id") instanceof Integer)) {
            return Result.error("001P067", "id参数类型不匹配");
        }
        if (requestBody.size() > 1) {
            return Result.error("001P068", "参数体包含多余参数");
        }
        Integer id      = (Integer) requestBody.get("id");
        Merchant merchant = merchantService.getById(id);
        if (merchant == null) {
            return Result.error("001B008", "merchant不存在");
        }
        String           filename = id + ".jpg";
        Optional<String> image64  = minioService.downloadBase64(bucket,filename);
        if (image64.isPresent()) {
            return Result.success(image64.get(), "merchant图片获取成功");
        }
        return Result.error("001M005", "merchant图片获取失败");
    }

    @ApiOperation("Upload the image of a merchant by its ID")
    @PostMapping("/upload")
    public Result<Void> uploadImageById(
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
        if (!minioService.upload(bucket, multipartFile, filename)) {
            return Result.error("001M004", "multipartFile上传失败");
        }
        return Result.success("multipartFile上传成功");
    }

    @ApiOperation("Insert a merchant")
    @PostMapping("/insert")
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
        if (!minioService.upload(bucket, multipartFile, filename)) {
            return Result.error("001M003", "merchant插入失败");
        }
        return Result.success(merchant, "merchant插入成功");
    }

    @ApiOperation("Update a merchant")
    @PostMapping("/update")
    public Result<Merchant> updateById(@RequestBody @NotNull Map<String, Object> requestBody) {
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

    @ApiOperation("Merchant Login")
    @PostMapping("/login")
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
            Merchant merchant = merchantService.getOne(new QueryWrapper<Merchant>()
                    .eq("phone", phone));
            if (merchant == null) {
                return Result.error("001B003", "账号不存在 登录失败");
            }
            if (!bCryptPasswordEncoder.matches(password, merchant.getPassword())) {
                return Result.error("001B009", "密码不匹配 登录失败");
            }
            // todo
            return Result.success("");
        } catch (Exception e) {
            return Result.error("001O001", e.getMessage());
        }
    }
}
