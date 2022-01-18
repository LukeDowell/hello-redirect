package dev.dowell.helloredirect.proxy;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.net.MalformedURLException;
import java.util.List;

@Configuration
public class ProxyConfiguration implements WebMvcConfigurer {

    @Value("${proxy.target}")
    private String proxyTarget;

    @Autowired
    private List<RequestParityHandler> handlerList;

    @Bean
    public ProxyInterceptor proxyInterceptor() throws MalformedURLException {
        return new ProxyInterceptor(new RestTemplate(), proxyTarget, handlerList);
    }

    @SneakyThrows
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(proxyInterceptor());
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/legacy/**").allowedOrigins("http://localhost:3000");
    }
}
