package com.gedoumi.crowd.common.config;

import com.gedoumi.crowd.component.AdminRequestInterceptor;
import com.gedoumi.crowd.component.ApiRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 项目相关配置
 *
 * @author Minced
 */
@Configuration
public class ProjectConfig implements WebMvcConfigurer {

    /**
     * 注册Api请求拦截器
     *
     * @return Api请求拦截器
     */
    @Bean
    public ApiRequestInterceptor apiRequestInterceptor() {
        return new ApiRequestInterceptor();
    }

    /**
     * 注册Admin请求拦截器
     *
     * @return Admin请求拦截器
     */
    @Bean
    public AdminRequestInterceptor adminRequestInterceptor() {
        return new AdminRequestInterceptor();
    }

    /**
     * 添加拦截器
     *
     * @param registry 拦截器注册器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiRequestInterceptor())
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/user/login");
        registry.addInterceptor(adminRequestInterceptor())
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/loginPage")
                .excludePathPatterns("/admin/login")
                .excludePathPatterns("/easyui/**");
    }

    /**
     * 跨域配置
     *
     * @param registry 跨域注册器
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowCredentials(true).maxAge(1800L);  // 跨域缓存以及缓存时间
    }

}
