package com.gedoumi.tg.common.config;

import com.gedoumi.tg.component.RequestInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 项目相关配置
 *
 * @author Minced
 */
@Configuration
public class ProjectConfig extends WebMvcConfigurationSupport {

    /**
     * 添加拦截器
     *
     * @param registry 拦截器注册器
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestInterceptor())
                .addPathPatterns("/**");
    }

}
