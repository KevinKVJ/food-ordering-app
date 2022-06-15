package app.ordering.food.controller;

import app.ordering.food.common.JwtUtils;
import app.ordering.food.entity.Merchant;
import app.ordering.food.service.MerchantService;
import app.ordering.food.service.MinioService;
import app.ordering.food.service.RedisService;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@Transactional
@Rollback
class MerchantControllerTest {

    private final String  baseUrl = "/api/v1/merchant";

    @Resource
    private       MockMvc mockMvc;

    @Resource
    private MinioService minioService;

    @Resource
    private MerchantService merchantService;

    @Test
    void getMerchants() throws Exception {
        String url = baseUrl + "/all";
        RequestBuilder request = MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("0000000"))
                .andReturn();
        mvcResult.getResponse().setCharacterEncoding("UTF-8");
        System.out.print(JSONUtil.toJsonPrettyStr(mvcResult.getResponse().getContentAsString()));
    }

    @Test
    void getMerchantById() throws Exception {
        String  url        = baseUrl + "/id";
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("id", 1);
        RequestBuilder request = MockMvcRequestBuilders.post(url)
                .content(new ObjectMapper().writeValueAsString(requestBody))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("0000000"))
                .andReturn();
        mvcResult.getResponse().setCharacterEncoding("UTF-8");
        System.out.print(JSONUtil.toJsonPrettyStr(mvcResult.getResponse().getContentAsString()));
    }

    @Test
    void insert() throws Exception {
        String url = baseUrl + "/insert";
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("phone", "1000000999");
        requestBody.put("password", "password");
        requestBody.put("name", "test");
        requestBody.put("description", "description");
        requestBody.put("address", "address");
        requestBody.put("publicPhone", "publicPhone");
        requestBody.put("publicAddress", "publicAddress");
        requestBody.put("businessHours","businessHours");
        RequestBuilder request = MockMvcRequestBuilders.post(url)
                .content(new ObjectMapper().writeValueAsString(requestBody))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("0000000"))
                .andReturn();
        mvcResult.getResponse().setCharacterEncoding("UTF-8");
        System.out.print(JSONUtil.toJsonPrettyStr(mvcResult.getResponse().getContentAsString()));

        String bucket = "food-ordering-app-merchants";
        String filename = "4.jpg";
        Assertions.assertTrue(minioService.existObject(bucket, filename));
        minioService.removeObject(bucket, filename);
    }

    @Test
    void downloadBase64() throws Exception {
        String url = baseUrl + "/image64";
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("id", 1);
        RequestBuilder request = MockMvcRequestBuilders.post(url)
                .content(new ObjectMapper().writeValueAsString(requestBody))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("0000000"))
                .andReturn();
        mvcResult.getResponse().setCharacterEncoding("UTF-8");
        System.out.print(JSONUtil.toJsonPrettyStr(mvcResult.getResponse().getContentAsString()));
    }

    @Test
    void loginAndLogout() throws Exception {
        String url = baseUrl + "/login";
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("phone", "1000000000");
        requestBody.put("password", "password1");
        RequestBuilder request = MockMvcRequestBuilders.post(url)
                .content(new ObjectMapper().writeValueAsString(requestBody))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("0000000"))
                .andReturn();
        mvcResult.getResponse().setCharacterEncoding("UTF-8");
        System.out.print(JSONUtil.toJsonPrettyStr(mvcResult.getResponse().getContentAsString()));

        Merchant merchant = merchantService.getOne(new QueryWrapper<Merchant>()
        .eq("phone", "1000000000"));
        String token = JwtUtils.createToken(merchant);

        url = baseUrl + "/logout";
        Map<String, Object> requestBody2 = new HashMap<>();
        requestBody2.put("token", token);
        RequestBuilder request2 = MockMvcRequestBuilders.post(url)
                .content(new ObjectMapper().writeValueAsString(requestBody2))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult2 = mockMvc.perform(request2)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("0000000"))
                .andReturn();
        mvcResult2.getResponse().setCharacterEncoding("UTF-8");
        System.out.print(JSONUtil.toJsonPrettyStr(mvcResult2.getResponse().getContentAsString()));
    }
}