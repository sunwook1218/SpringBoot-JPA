package com.example.demo.common.config.mustache;

import org.springframework.boot.autoconfigure.mustache.MustacheResourceTemplateLoader;
import org.springframework.boot.web.servlet.view.MustacheViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.servlet.ViewResolver;

@Configuration
public class MustacheConfig {

    @Bean
    public ViewResolver getViewResolver(ResourceLoader resourceLoader) {

        MustacheViewResolver mustacheViewResolver
                = new MustacheViewResolver();
        mustacheViewResolver.setPrefix("/WEB-INF/views/");
        mustacheViewResolver.setSuffix("..mustache");
        mustacheViewResolver.setCache(false);

        MustacheResourceTemplateLoader mustacheResourceTemplateLoader
                = new MustacheResourceTemplateLoader();
        mustacheResourceTemplateLoader.setResourceLoader(resourceLoader);

//        mustacheViewResolver.setTemplateLoader(mustacheResourceTemplateLoader);

        return mustacheViewResolver;

    }

}
