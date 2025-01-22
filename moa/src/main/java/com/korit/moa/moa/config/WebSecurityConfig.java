package com.korit.moa.moa.config;

import com.korit.moa.moa.filter.JwtAuthenticationFilter;

import com.korit.moa.moa.handler.OAuth2SuccessHandler;
import com.korit.moa.moa.service.implement.OAuth2UserServiceImplement;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    @Lazy
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;
    private final OAuth2UserServiceImplement oAuth2Service;

    @Bean
    public CorsFilter corsFilter() {

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                new AntPathRequestMatcher("/api/v1/auth/**"),
                                new AntPathRequestMatcher("/image/**"),
                                new AntPathRequestMatcher("/hobbies/**"),
                                new AntPathRequestMatcher("/api/v1/reviews/auth"),
                                new AntPathRequestMatcher("/api/v1/mails/**"),
                                new AntPathRequestMatcher("/api/v1/notices/**"),
                                new AntPathRequestMatcher("/oauth2/callback/*")
                        )
                        .permitAll()
                        .anyRequest().authenticated())
                .oauth2Login(oauth2 -> oauth2
                        .redirectionEndpoint(endpoint -> endpoint.baseUri("/oauth2/callback/*"))
                        .authorizationEndpoint(endpoint -> endpoint.baseUri("/api/v1/auth/sns-sign-in"))
                        .userInfoEndpoint(endpoint -> endpoint.userService(oAuth2Service))
                        .successHandler(oAuth2SuccessHandler)
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(BCryptPasswordEncoder bCryptpasswordEncoder) throws Exception {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setPasswordEncoder(bCryptpasswordEncoder);

        return new ProviderManager(List.of(authProvider));
    }

    @Bean
    public BCryptPasswordEncoder bCryptpasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}