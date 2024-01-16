package com.example.springjwtprac.config;

import com.example.springjwtprac.jwt.JWTFilter;
import com.example.springjwtprac.jwt.JWTUtil;
import com.example.springjwtprac.jwt.LoginFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * 비밀번호 해쉬 암호화를 위한 인코더 빈 등록
     * 1. 해당 인코더를 사용하지 않았을 경우, 생기는 문제점은?
     */

    private final AuthenticationConfiguration authenticationConfiguration;
    private final JWTUtil jwtUtil; // 이미 @Componenet로 등록되어 스프링에서 관리하는 빈 객체임

    public SecurityConfig(AuthenticationConfiguration authenticationConfiguration, JWTUtil jwtUtil) {
        this.authenticationConfiguration = authenticationConfiguration;
        this.jwtUtil = jwtUtil;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)
            throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        /**
         * csrf disable 설정 - 세션 방식과는 다르게 csrf를 disable로 설정함
         * 1. csrf가 무엇인가?
         * 2. 세션방식에서는 왜 csrf에 대한 설정을 해야 하는가
         * 3. 왜 JWT 방식에서는 세션이 stateless이어야 하는가
         * 4. 빌더 패턴
         * 5. 스프링 필터
         * 6. 빈 등록 관련 내용 복습 및 정리
         */
        http
                .csrf((auth) -> auth.disable());

        // Form 로그인 방식 disable
        http
                .formLogin((auth) -> auth.disable());

        // http basic 인증 방식 disable
        http
                .httpBasic((auth) -> auth.disable());

        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/login", "/", "/join").permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .anyRequest().authenticated());
        
        // JWT 필터는 로그인 필터보다 앞에서 동작함
        http
                .addFilterBefore(new JWTFilter(jwtUtil), LoginFilter.class);

        http
                .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil),
                        UsernamePasswordAuthenticationFilter.class);

        // http 세션 설정 - JWT 방식에서는 stateless 상태로 설정해야 함!
        http
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}
