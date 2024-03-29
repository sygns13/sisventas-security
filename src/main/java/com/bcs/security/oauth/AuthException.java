package com.bcs.security.oauth;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class AuthException implements AuthenticationEntryPoint{

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException arg2)
            throws IOException, ServletException {
        final Map<String, Object> mapException = new HashMap<>();

        mapException.put("error", "401");
        mapException.put("message", "No estas autorizado para acceder a este recurso 2");
        mapException.put("exception", "No autorizado");
        mapException.put("path", request.getServletPath());
        mapException.put("timestamp", LocalDateTime.now());

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), mapException);
    }
}
