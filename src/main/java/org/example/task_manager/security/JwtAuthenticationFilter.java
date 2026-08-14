package org.example.task_manager.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        System.out.println(
                "JWT FILTER: "
                        + request.getMethod()
                        + " "
                        + request.getRequestURI()
        );

        String authHeader =
                request.getHeader("Authorization");

        if (authHeader == null ||
                !authHeader.startsWith("Bearer ")) {

            filterChain.doFilter(request, response);
            return;
        }
        String jwt =
                authHeader.substring(7);
        String username =
                jwtService.extractUsername(jwt);
        System.out.println(
                "JWT username = " + username);
        if (username != null &&
                SecurityContextHolder
                        .getContext()
                        .getAuthentication() == null) {



            // 6. Загружаем пользователя
            UserDetails userDetails =
                    userDetailsService
                            .loadUserByUsername(username);

            // 7. Проверяем JWT

            if (jwtService.isTokenValid(
                    jwt,
                    userDetails)) {

                // 8. Создаём Authentication
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                // 9. Передаём информацию о пользователе
                // в SecurityContext
                SecurityContextHolder
                        .getContext()
                        .setAuthentication(authToken);
            }
        }

        // 10. Продолжаем обработку запроса
        filterChain.doFilter(request, response);
    }
}
