package ua.com.owu.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("ua.com.owu")
public class WebConfig extends WebMvcConfigurerAdapter {

    @Bean
    public FreeMarkerViewResolver viewResolver() {
        FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
        viewResolver.setCache(true);
//        viewResolver.setPrefix("");
//        viewResolver.setPrefix("/pages/"); try insteed freeMarkerConfigurer
        viewResolver.setSuffix(".ftl");
        viewResolver.setContentType("text/html; charset=UTF-8");
        return viewResolver;
    }

    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer() {
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        configurer.setTemplateLoaderPath("/pages/");
        configurer.setDefaultEncoding("UTF-8");

        return configurer;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/style/**").addResourceLocations("/static/styles/");
        registry.addResourceHandler("/images/**").addResourceLocations("/images/");
        registry.addResourceHandler("/script/**").addResourceLocations("/static/scripts/");
        registry.addResourceHandler("/externalLibsJs/jQuery/**").addResourceLocations("/static/externalLibsJs/jQuery/");
        registry.addResourceHandler("/externalLibsJs/select2JS/**").addResourceLocations("/static/externalLibsJs/select2-4.0.3/dist/js/");
        registry.addResourceHandler("/externalLibsJs/select2CSS/**").addResourceLocations("/static/externalLibsJs/select2-4.0.3/dist/css/");
    }
}
