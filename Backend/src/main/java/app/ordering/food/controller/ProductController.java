package app.ordering.food.controller;

import app.ordering.food.common.Result;
import app.ordering.food.entity.Product;
import app.ordering.food.service.ProductService;
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
@RequestMapping("api/v1/product")
@Api(tags = "Product Controller")
public class ProductController {
    @Resource
    private ProductService productService;

    @ApiOperation("Get all products")
    @GetMapping("/all")
    public Result<List<Product>> getProducts() {
        return productService.getProducts();
    }

    @ApiOperation("Get a product without its image")
    @PostMapping("/one")
    public Result<Product> getProductById(@RequestBody @NotNull Map<String, Object> requestBody) {
        return productService.getProductById(requestBody);
    }

    @ApiOperation("Get the image of a product by its ID")
    @PostMapping("/image")
    public ResponseEntity<byte[]> getImageById(@RequestBody @NotNull Map<String, Object> requestBody) {
        return productService.getImageById(requestBody);
    }

    @ApiOperation("Upload the image of a product by its ID")
    @PostMapping("/upload")
    public Result<Void> uploadImageById(
            @RequestParam("id") @NotNull Integer id,
            @RequestPart("file") @NotNull MultipartFile file
    ) {
        return productService.uploadImageById(id, file);
    }

    @ApiOperation("Insert a product")
    @PostMapping("/insert")
    public Result<Product> insert(@RequestBody @NotNull Map<String, Object> requestBody) {
        return productService.insert(requestBody);
    }

    @ApiOperation("Update a product")
    @PostMapping("/update")
    public Result<Product> updateById(@RequestBody @NotNull Map<String, Object> requestBody) {
        return productService.updateById(requestBody);
    }
}
