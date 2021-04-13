package flowcontrol.gateway.security;

import flowcontrol.gateway.service.CustomUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Value("${app.jwt.header}")
    private String tokenRequestHeader;

    @Value("${app.jwt.header.prefix}")
    private String tokenRequestHeaderPrefix;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private JwtTokenValidator jwtTokenValidator;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = getJwtFromRequest(request);

            if(StringUtils.hasText(jwt) && jwtTokenValidator.validateToken(jwt)) {
                Long userId = jwtTokenProvider.getUserIdFromJwt(jwt);

                UserDetails userDetails = customUserDetailsService.loadUserById(userId);

                UsernamePasswordAuthenticationToken authenticationToken= new UsernamePasswordAuthenticationToken(userDetails, jwt, userDetails.getAuthorities());

                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        } catch (Exception exception){
            log.error("Failed to set user authentication in security context ", exception);
            throw exception;
        }

        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request){
        String bearerToken = request.getHeader(tokenRequestHeader);
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith(tokenRequestHeaderPrefix)){
            log.info("Extracted Token: " + bearerToken);
            return bearerToken.replace(tokenRequestHeaderPrefix, "");
        }
        return null;
    }
}
