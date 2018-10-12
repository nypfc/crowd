package com.gedoumi.crowd.common.config;

import com.gedoumi.crowd.component.AdminRequestInterceptor;
import com.gedoumi.crowd.component.ApiRequestInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
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
     * 跨域过滤器配置
     *
     * @return Filter注册器
     */
    @Bean
    @SuppressWarnings("unchecked")
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.setAllowCredentials(true);  // 允许跨域缓存
        config.setMaxAge(3600L);  // 缓存时间
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);  // 跨域Filter排序，排到第一个
        return bean;
    }

}
