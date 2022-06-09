package app.ordering.food.controller;

import app.ordering.food.common.Result;
import app.ordering.food.entity.Category;
import app.ordering.food.entity.Merchant;
import app.ordering.food.entity.Product;
import app.ordering.food.service.CategoryService;
import app.ordering.food.service.MerchantService;
import app.ordering.food.service.ProductService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/category")
@Api(tags = "Category Controller")
public class CategoryController {

    @Resource
    private MerchantService merchantService;

    @Resource
    private ProductService productService;

    @Resource
    private CategoryService categoryService;

    @ApiOperation("Get all categories")
    @GetMapping("/all")
    public Result<List<Category>> getCategories() {
        List<Category> categories = categoryService.list();
        if (categories == null) {
            return Result.error("005M001", "获取category列表失败");
        }
        return Result.success(categories, "获取category列表成功");
    }

    @ApiOperation("Get all categories of a merchant by its ID")
    @PostMapping("/merchant")
    public Result<List<Category>> getCategoriesByMerchantId(@RequestBody @NotNull Map<String, Object> requestBody) {
        if (requestBody == null) {
            return Result.error("005P001","参数体为null");
        }
        if (!requestBody.containsKey("merchantId")) {
            return Result.error("005P002", "参数体不包含merchant id");
        }
        if (requestBody.get("merchantId") == null) {
            return Result.error("005P003", "merchant id为null");
        }
        if (!(requestBody.get("merchantId") instanceof Integer)) {
            return Result.error("005P004", "merchant id参数类型不匹配");
        }
        if (requestBody.size() > 1) {
            return Result.error("005P005", "参数体包含多余参数");
        }
        Integer  merchantId = (Integer) requestBody.get("merchantId");
        Merchant merchant   = merchantService.getById(merchantId);
        if (merchant == null) {
            return Result.error("005B001", "merchant id不存在");
        }
        List<Category> categories = categoryService.listByMerchantId(merchantId);
        if (categories == null) {
            return Result.error("005M002", "获取category列表失败");
        }
        return Result.success(categories, "获取category列表成功");
    }

    @ApiOperation("Get a category by its ID")
    @PostMapping("/id")
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

    @ApiOperation("Get all categories of a product by the product's ID")
    @PostMapping("/product")
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

}
