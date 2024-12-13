package com.korit.moa.moa.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${user.dir}")
    private String projectPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        String rootPath = projectPath + "/image/";
        registry.addResourceHandler("/image/**")
                .addResourceLocations("file:///" + rootPath)
                .resourceChain(true)
                .addResolver(new PathResourceResolver() {
                    @Override
                    protected Resource getResource(String resourcePath, Resource location) throws IOException {
                        resourcePath = URLDecoder.decode(resourcePath, StandardCharsets.UTF_8);
                        return super.getResource(resourcePath, location);
                    }
                });
    }
}
