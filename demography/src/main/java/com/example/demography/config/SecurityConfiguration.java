//package com.example.demography.config;
//
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfiguration {
//
//    @Bean
//    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
//        http
//                .requiresChannel(channel -> channel
//                        .anyRequest().requiresSecure()
//                )
//                .exceptionHandling(exceptionHandling ->
//                exceptionHandling
//                        .accessDeniedHandler((request, response, accessDeniedException) -> {
//                            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Использование HTTP недопустимо. Пожалуйста, используйте HTTPS.");
//                        })
//        );
//        return http.build();
//    }
//}
