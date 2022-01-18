package dev.dowell.helloredirect.proxy;

import lombok.Data;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface RequestParityHandler {
    boolean shouldHandle(HttpServletRequest request);
    boolean handleRequest(HttpServletRequest request,
                          HttpServletResponse response,
                          Object handler) throws IOException;
}
