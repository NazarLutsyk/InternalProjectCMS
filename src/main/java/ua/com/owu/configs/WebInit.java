package ua.com.owu.configs;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;
import java.util.TimeZone;

public class WebInit implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(WebConfig.class);
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);

        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Kyiv"));

        FilterRegistration.Dynamic filterRegistration =
                servletContext.addFilter("encodingFilter", encodingFilter);
        filterRegistration.addMappingForUrlPatterns(null, true, "/*");

        DispatcherServlet dispatcherServlet = new DispatcherServlet(context);
        ServletRegistration.Dynamic registration = servletContext.addServlet("dispatcherServlet", dispatcherServlet);
        registration.setLoadOnStartup(1);
        registration.setMultipartConfig(new MultipartConfigElement("",10000000,10000000,10000000));

        registration.addMapping("/");


    }
}
