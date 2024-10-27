package com.example.yeoga.jwt;

import com.example.yeoga.dto.CustomOAuth2User;
import com.example.yeoga.dto.UserDTO;
import com.example.yeoga.dto.CustomUserDetails;
import com.example.yeoga.entity.UserEntity;
import com.example.yeoga.jwt.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.ExpiredJwtException;

import java.io.IOException;
import java.io.PrintWriter;

public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    public JWTFilter(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String token = extractToken(request);
        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            if (jwtUtil.isExpired(token)) {
                handleExpiredToken(response, "토큰이 만료되었습니다.");
                return;
            }
        } catch (ExpiredJwtException e) {
            handleExpiredToken(response, "액세스 토큰이 만료되었습니다.");
            return;
        }

        String category = jwtUtil.getCategory(token);
        String username = jwtUtil.getUsername(token);
        String role = jwtUtil.getRole(token);

        if (category == null) {
            // 카테고리가 없을 경우 기본 동작을 결정하거나 토큰을 거부
            handleInvalidToken(response, "유효하지 않은 토큰 카테고리입니다.");
            return;
        }

        Authentication authToken = createAuthentication(category, username, role);
        if (authToken != null) {
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }

        filterChain.doFilter(request, response);
    }

    /**
     * JWT 토큰을 Authorization 헤더 또는 쿠키에서 추출합니다.
     *
     * @param request HTTP 요청
     * @return JWT 토큰 또는 null
     */
    private String extractToken(HttpServletRequest request) {
        // Authorization 헤더에서 토큰 추출 시도
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }

        // 헤더에 없을 경우 쿠키에서 토큰 추출
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("Authorization".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }

        return null;
    }

    /**
     * 토큰 카테고리에 따라 Authentication 객체를 생성합니다.
     *
     * @param category 토큰 카테고리 (예: "access", "oauth")
     * @param username 사용자 이름
     * @param role     사용자 역할
     * @return Authentication 객체 또는 null
     */
    private Authentication createAuthentication(String category, String username, String role) {
        if ("access".equals(category)) {
            // 일반 JWT 인증
            UserEntity userEntity = new UserEntity();
            userEntity.setUsername(username);
            userEntity.setRole(role);
            CustomUserDetails customUserDetails = new CustomUserDetails(userEntity);
            return new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
        } else if ("oauth".equals(category)) {
            // OAuth JWT 인증
            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(username);
            userDTO.setRole(role);
            CustomOAuth2User customOAuth2User = new CustomOAuth2User(userDTO);
            return new UsernamePasswordAuthenticationToken(customOAuth2User, null, customOAuth2User.getAuthorities());
        }
        // 필요에 따라 추가적인 카테고리 처리
        return null;
    }

    /**
     * 만료된 토큰에 대해 401 응답을 반환합니다.
     *
     * @param response HTTP 응답
     * @param message  에러 메시지
     * @throws IOException 입출력 예외
     */
    private void handleExpiredToken(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        PrintWriter writer = response.getWriter();
        writer.write(message);
        writer.flush();
    }

    /**
     * 유효하지 않은 토큰에 대해 401 응답을 반환합니다.
     *
     * @param response HTTP 응답
     * @param message  에러 메시지
     * @throws IOException 입출력 예외
     */
    private void handleInvalidToken(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        PrintWriter writer = response.getWriter();
        writer.write(message);
        writer.flush();
    }
}