package com.example.studentmanagment.configuration;

import com.example.studentmanagment.entity.UserType;
import com.example.studentmanagment.security.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final PasswordEncoder passwordEncoder;
    private final UserDetailService userDetailService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/").permitAll()
                .requestMatchers("/all/lessons").permitAll()
                .requestMatchers("/students").permitAll()
                .requestMatchers("/teachers").permitAll()
                .requestMatchers("/login/page").permitAll()
                .requestMatchers("/user/register").permitAll()
                .requestMatchers("/my/lessons").hasAnyAuthority(UserType.TEACHER.name())
                .requestMatchers("/add/lesson/page").hasAnyAuthority(UserType.TEACHER.name())
                .requestMatchers("/register/lesson/").hasAnyAuthority(UserType.STUDENT.name())
                .requestMatchers("/chat/").hasAnyAuthority(UserType.STUDENT.name())
                .requestMatchers("/send").hasAnyAuthority(UserType.STUDENT.name())
                .requestMatchers("/user/profile").hasAnyAuthority(UserType.STUDENT.name(), UserType.TEACHER.name())
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/login/page")
                .loginProcessingUrl("/login")
                .successForwardUrl("/")
                .defaultSuccessUrl("/", true)
                .and()
                .logout()
                .logoutSuccessUrl("/");
        return httpSecurity.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        return daoAuthenticationProvider;
    }
}