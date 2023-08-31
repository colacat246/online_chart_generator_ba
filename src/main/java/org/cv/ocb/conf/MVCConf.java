package org.cv.ocb.conf;

import org.cv.ocb.handler.LogInInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MVCConf implements WebMvcConfigurer {
    @Autowired
    private LogInInterceptor logInInterceptor;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedMethods("POST", "GET", "DELETE")
                .allowedOrigins("https://localhost:5173", "https://127.0.0.1:5173");
//                .allowedOriginPatterns("https://localhost:*");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInInterceptor).addPathPatterns("/api/**").excludePathPatterns("/api/login", "/api/register");
    }
}
