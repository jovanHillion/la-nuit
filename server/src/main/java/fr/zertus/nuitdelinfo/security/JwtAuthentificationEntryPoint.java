package fr.zertus.nuitdelinfo.security;

import com.google.gson.Gson;
import fr.zertus.nuitdelinfo.model.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import java.io.IOException;

public class JwtAuthentificationEntryPoint extends BasicAuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().println(new Gson().toJson(ApiResponse.unauthorized("You are not authorized to access this resource.")));
    }

    @Override
    public void afterPropertiesSet() {
        setRealmName("ZenNetwork JWT");
        super.afterPropertiesSet();
    }

}
