package org.cv.ocb.conf;

import lombok.RequiredArgsConstructor;
import org.cv.ocb.handler.LogInInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class MVCConf implements WebMvcConfigurer {
    private final LogInInterceptor logInInterceptor;

    // 开发时有前端代理了，暂时不需要后端配置跨域了
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowCredentials(true)
//                .allowedMethods("POST", "GET", "DELETE", "PUT")
//                .allowedOrigins("https://localhost:5173", "https://127.0.0.1:5173");
//    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInInterceptor).addPathPatterns("/api/**").excludePathPatterns("/api/login", "/api/register");
    }
}
