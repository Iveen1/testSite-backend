package ru.iveen.testsite.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Polyakov Anton
 * @created 12.05.2022 23:16
 * @project testSite
 */
@Configuration
public class ResourceConfig implements WebMvcConfigurer {
    @Value("${storage.path}")
    private String path;
//    file:///D:/!!!!!!ITHUB/!gitRepos/personalProjects/testSite/storage/
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/storage/**").addResourceLocations("file:" + path);
    }
}
