package com.example.carrental.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AppConfig {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Sử dụng BCryptPasswordEncoder để mã hóa mật khẩu
    }


    @Bean
    public UserDetailsService userDetailsService() {
        return username -> User.builder()
                .username("dat")  // Tên người dùng
                .password(passwordEncoder().encode("12345"))  // Mật khẩu mã hóa
                .roles("USER")  // Vai trò người dùng
                .build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Tắt CSRF cho ứng dụng không phải REST API
                .csrf(csrf -> csrf.disable())

                // Cấu hình quyền truy cập cho các URL
                .authorizeHttpRequests(auth ->
                        auth
                                // Cho phép truy cập các trang Swagger UI và API docs mà không cần đăng nhập
                                .requestMatchers("/swagger-ui/**", "/api-docs/**").permitAll()
                                // Cho phép truy cập các trang đăng nhập và reset mật khẩu mà không cần đăng nhập
                                .requestMatchers("/login", "/forgot-password", "/reset-password").permitAll()
                                .requestMatchers("/api/auth/login", "/api/auth/register").permitAll()
                                // Cho phép các URL khác yêu cầu người dùng đã đăng nhập
                                .anyRequest().authenticated()
                )

                // Cấu hình trang đăng nhập
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/login")  // Trang đăng nhập
                                .loginProcessingUrl("/perform_login")  // URL xử lý đăng nhập
                                .defaultSuccessUrl("/home", true)  // URL sau khi đăng nhập thành công
                                .failureUrl("/login?error=true")  // URL khi đăng nhập thất bại
                )

                // Cấu hình RememberMe (ghi nhớ đăng nhập)
                .rememberMe(rememberMe ->
                        rememberMe
                                .key("uniqueAndSecret")  // Khóa bí mật cho RememberMe
                                .tokenValiditySeconds(600)  // Thời gian hiệu lực (600 giây)
                )

                // Cấu hình logout
                .logout(logout ->
                        logout
                                .logoutUrl("/logout")  // URL logout
                                .logoutSuccessUrl("/login?logout=true")  // URL sau khi logout thành công
                );

        return http.build();
    }
}
