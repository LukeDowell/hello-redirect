package dev.dowell.helloredirect.proxy;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@Component
public class PasswordRequestParityHandler implements RequestParityHandler {

    private final ObjectMapper objectMapper;
    private final ProxyController proxyController;

    public PasswordRequestParityHandler(
        ObjectMapper objectMapper,
        ProxyController proxyController) {
        this.objectMapper = objectMapper;
        this.proxyController = proxyController;
    }

    @Override
    public boolean shouldHandle(HttpServletRequest request) {
        return request.getServletPath().contains("/legacy/password");
    }

    @Override
    public boolean handleRequest(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        var maybeBody = request.getReader().lines().collect(Collectors.joining());
        var serializedObject = objectMapper.readValue(maybeBody, PasswordReset.class);
        var ourResponse = proxyController.resetPassword(serializedObject);

        // Send them our new response
        response.getWriter().write(objectMapper.writeValueAsString(ourResponse));


        return false;
    }
}
