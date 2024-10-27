package com.example.yeoga.config;

import com.example.yeoga.jwt.JWTFilter;
import com.example.yeoga.jwt.JWTUtil;
import com.example.yeoga.oauth2.CustomSuccessHandler;
import com.example.yeoga.service.CustomOAuth2UserService;
import com.example.yeoga.jwt.CustomLogoutFilter;
import com.example.yeoga.jwt.LoginFilter;
import com.example.yeoga.repository.RefreshRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomSuccessHandler customSuccessHandler;
    private final JWTUtil jwtUtil;
    private final AuthenticationConfiguration authenticationConfiguration;
    private final RefreshRepository refreshRepository;

    public SecurityConfig(CustomOAuth2UserService customOAuth2UserService,
                          CustomSuccessHandler customSuccessHandler,
                          JWTUtil jwtUtil,
                          AuthenticationConfiguration authenticationConfiguration,
                          RefreshRepository refreshRepository) {
        this.customOAuth2UserService = customOAuth2UserService;
        this.customSuccessHandler = customSuccessHandler;
        this.jwtUtil = jwtUtil;
        this.authenticationConfiguration = authenticationConfiguration;
        this.refreshRepository = refreshRepository;
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // CORS 설정
        http.cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration configuration = new CorsConfiguration();
                configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
                configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                configuration.setAllowCredentials(true);
                configuration.setAllowedHeaders(Collections.singletonList("*"));
                configuration.setMaxAge(3600L);
                configuration.setExposedHeaders(Arrays.asList("Set-Cookie", "Authorization", "access"));
                return configuration;
            }
        }));

        // CSRF 비활성화
        http.csrf(csrf -> csrf.disable());

        // 폼 로그인 및 HTTP Basic 인증 비활성화
        http.formLogin(form -> form.disable())
                .httpBasic(httpBasic -> httpBasic.disable());

        // 세션을 상태 비저장으로 설정
        http.sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // 인가 규칙 설정
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/login", "/join", "/reissue", "/v3/api-docs/**", "/swagger-ui/**").permitAll()
                .requestMatchers("/my").hasRole("USER")
                .requestMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated());

        // 필터 추가
        // JWTFilter: 모든 요청 전에 JWT 검증
        http.addFilterBefore(new JWTFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

        // LoginFilter: 로그인 요청을 처리
        http.addFilterAt(new LoginFilter(authenticationManager(), jwtUtil, refreshRepository),
                UsernamePasswordAuthenticationFilter.class);

        // CustomLogoutFilter: 로그아웃 요청을 처리
        http.addFilterBefore(new CustomLogoutFilter(jwtUtil, refreshRepository),
                LogoutFilter.class);

        // OAuth2 로그인 설정
        http.oauth2Login(oauth2 -> oauth2
                .userInfoEndpoint(userInfo -> userInfo
                        .userService(customOAuth2UserService))
                .successHandler(customSuccessHandler));

        return http.build();
    }
}