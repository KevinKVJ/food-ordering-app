package app.ordering.food.controller;

import app.ordering.food.common.Result;
import app.ordering.food.common.ResultEnum;
import app.ordering.food.entity.Merchant;
import app.ordering.food.service.MerchantService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("api/v1/merchant")
@Api(tags = "Merchant Controller")
public class MerchantController {
    @Resource
    private MerchantService merchantService;

    @GetMapping("/all")
    public Result<List<Merchant>> list() {
        List<Merchant> merchants = merchantService.list();
        if (merchants == null) {
            return Result.error(ResultEnum.BAD_REQUEST);
        }
        return Result.success(merchants);
    }
}
