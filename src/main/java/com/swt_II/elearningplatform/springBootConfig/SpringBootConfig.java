package com.swt_II.elearningplatform.springBootConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.util.Locale;
import java.util.TimeZone;

@Configuration
@EnableScheduling
public class SpringBootConfig implements WebMvcConfigurer {

    private final LocaleChangeInterceptor localeChangeInterceptor;

    public SpringBootConfig() {
        this.localeChangeInterceptor = localeChangeInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry) {
        interceptorRegistry.addInterceptor(localeChangeInterceptor);
    }

    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        localeResolver.setDefaultLocale(Locale.GERMAN);
        localeResolver.setDefaultTimeZone(TimeZone.getTimeZone("UTC"));

        return localeResolver;
    }

    private LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("localeData");

        return localeChangeInterceptor;
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**", "/templates/CSS/**")
                .addResourceLocations("classpath:/static/")
                .setCachePeriod(0)  // Set the cache period to 0 to disable caching
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.TEXT_HTML)
                .mediaType("css", MediaType.valueOf("text/css"));
    }

}
