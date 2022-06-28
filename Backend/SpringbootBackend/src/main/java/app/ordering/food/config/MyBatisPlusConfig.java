package app.ordering.food.config;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.extension.MybatisMapWrapperFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@MapperScan("app.ordering.food.mapper")
public class MyBatisPlusConfig {

    /**
     * Keep camel-case naming convention when querying for partial fields
     */
    @Bean
    public ConfigurationCustomizer myBatisConfigurationCustomizer() {
        return configuration -> configuration.setObjectWrapperFactory(new MybatisMapWrapperFactory());
    }

}