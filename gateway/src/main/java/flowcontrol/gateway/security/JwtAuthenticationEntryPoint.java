package flowcontrol.gateway.security;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
//@AllArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final HandlerExceptionResolver resolver;

    @Autowired
    public JwtAuthenticationEntryPoint(@Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver){
        this.resolver = resolver;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        log.error("User is unauthorized. Routing from the entry point");

        if(request.getAttribute("javax.servlet.error.exception") != null){
            Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
            resolver.resolveException(request, response, null, (Exception) throwable);
        }

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
    }
}
