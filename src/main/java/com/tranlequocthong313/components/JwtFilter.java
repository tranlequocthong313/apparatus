package com.tranlequocthong313.components;

import com.tranlequocthong313.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.util.StringUtils.hasText;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String AUTH_TYPE = "Bearer ";
    private static final int TOKEN_START = 7;

    private final JwtProvider jwtProvider;
    private final UserService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = getTokenFromRequest(request);
        if (token != null) {
            var loginFromToken = jwtProvider.getLoginFromToken(token);
            var userDetails = customUserDetailsService.loadUserByUsername(loginFromToken);
            if (userDetails != null) {
                var auth = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        var bearer = request.getHeader(AUTHORIZATION_HEADER);
        if (validateBearer(bearer)) {
            return bearer.substring(TOKEN_START);
        }
        return null;
    }

    private boolean validateBearer(String bearer) {
        return hasText(bearer) && bearer.startsWith(AUTH_TYPE);
    }
}

