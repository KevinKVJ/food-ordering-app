package app.ordering.food.controller;

import app.ordering.food.common.Result;
import app.ordering.food.entity.Merchant;
import app.ordering.food.service.MerchantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/merchant")
@Api(tags = "Merchant Controller")
public class MerchantController {
    @Resource
    private MerchantService merchantService;

    @ApiOperation("Get all merchants")
    @GetMapping("/all")
    public Result<List<Merchant>> getMerchants() {
        return merchantService.getMerchants();
    }

    @ApiOperation("Get a merchant without its image")
    @PostMapping("/one")
    public Result<Merchant> getMerchantById(@RequestBody @NotNull Map<String, Object> requestBody) {
        return merchantService.getMerchantById(requestBody);
    }

    @ApiOperation("Get the image of a merchant")
    @PostMapping("/image")
    public ResponseEntity<byte[]> getImageById(@RequestBody @NotNull Map<String, Object> requestBody) {
        return merchantService.getImageById(requestBody);
    }

    @ApiOperation("Upload the image of a merchant by its ID")
    @PostMapping("/upload")
    public Result<Void> uploadImageById(
            @RequestParam("id") @NotNull Integer id,
            @RequestPart("file") @NotNull MultipartFile file
    ) {
        return merchantService.uploadImageById(id, file);
    }

    @ApiOperation("Insert a merchant")
    @PostMapping("/insert")
    public Result<Merchant> insert(@RequestBody @NotNull Map<String, Object> requestBody) {
        return merchantService.insert(requestBody);
    }

    @ApiOperation("Update a merchant")
    @PostMapping("/update")
    public Result<Merchant> updateById(@RequestBody @NotNull Map<String, Object> requestBody) {
        return merchantService.updateById(requestBody);
    }

    @ApiOperation("Merchant Login")
    @PostMapping("/login")
    public Result<String> login(@RequestBody @NotNull Map<String, Object> requestBody) {
        return merchantService.login(requestBody);
    }
}
