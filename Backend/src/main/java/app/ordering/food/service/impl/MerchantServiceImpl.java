package app.ordering.food.service.impl;

import app.ordering.food.common.Result;
import app.ordering.food.entity.Merchant;
import app.ordering.food.mapper.MerchantMapper;
import app.ordering.food.service.MerchantService;
import app.ordering.food.service.MinioService;
import cn.hutool.core.io.IoUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service("merchantService")
public class MerchantServiceImpl extends ServiceImpl<MerchantMapper, Merchant> implements MerchantService {

    @Resource
    private MinioService minioService;

    @Value("${minio.bucketForMerchants}")
    private String bucket;

    @Override
    //@Cacheable(value = "merchantServiceCache")
    public List<Merchant> list() {
        return super.list();
    }

    @Override
    //@Cacheable(value = "merchantServiceCache", key = "#id")
    public Merchant getById(Serializable id) {
        return super.getById(id);
    }

    @Override
    public boolean save(Merchant merchant) {
        return super.save(merchant);
    }

    @Override
    public Merchant getOne(Wrapper<Merchant> wrapper) {
        return super.getOne(wrapper);
    }

    @Override
    public boolean updateById(Merchant merchant) {
        return super.updateById(merchant);
    }
}