package com.groupHVC.CsHTTT.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path mainPUploadDir = Paths.get("./product-mainPicture");
        String mainPUploadPath = mainPUploadDir.toFile().getAbsolutePath();

        registry.addResourceHandler("/product-mainPicture/**").addResourceLocations("file:/" + mainPUploadPath + "/");
    }
}
