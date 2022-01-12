package dev.dowell.helloredirect.proxy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

@Slf4j
public class ProxyInterceptor implements HandlerInterceptor {

    private final RestTemplate restTemplate;
    private final URL proxyTarget;

    public ProxyInterceptor(RestTemplate restTemplate,
                            String proxyTarget) throws MalformedURLException {
        this.restTemplate = restTemplate;
        this.proxyTarget = new URL(proxyTarget);
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws IOException, URISyntaxException {
        if (request.getServletPath().contains("/legacy/")) {
            log.info("Proxying request to legacy service...");
            var maybeBody = request.getReader().lines();
            var proxyResponse = restTemplate.exchange(
                    new URI(proxyTarget.toString().concat(request.getServletPath())),
                    HttpMethod.valueOf(request.getMethod()),
                    null, //TODO for post, put, etc
                    String.class
            );

            log.info("Proxied request returned with status {}", proxyResponse.getStatusCodeValue());

            proxyResponse.getHeaders().forEach((String h, List<String> v) -> {
                response.setHeader(h, v.get(0));
            });
            response.setStatus(proxyResponse.getStatusCodeValue());

            var proxyResponseBody = proxyResponse.getBody();
            if (proxyResponseBody != null) {
                response.getWriter().write(proxyResponseBody);
            }
            return false;
        }
        return true;
    }
}
