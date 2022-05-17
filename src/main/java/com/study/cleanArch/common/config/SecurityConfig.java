package com.study.cleanArch.common.config;

import com.study.cleanArch.member.application.port.in.GetMemberQuery;
import com.study.cleanArch.common.jwt.JwtAuthenticationFilter;
import com.study.cleanArch.common.jwt.JwtAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.filter.CorsFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CorsFilter corsFilter;
    private final GetMemberQuery getMemberQuery;


    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
            .antMatchers(
                "/h2-console/**"
                , "/favicon.ico"
                , "/error"
            );
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(
            authenticationManager());
        jwtAuthenticationFilter.setFilterProcessesUrl("/api/member/login");
        httpSecurity
            // token을 사용하는 방식이기 때문에 csrf를 disable합니다.
            .csrf().disable()

            .addFilter(corsFilter)
            .formLogin().disable()
            .httpBasic().disable()
            .addFilter(jwtAuthenticationFilter)
            .addFilter(new JwtAuthorizationFilter(authenticationManager(),getMemberQuery))
            .exceptionHandling()
//            .authenticationEntryPoint(jwtAuthenticationEntryPoint)
//            .accessDeniedHandler(jwtAccessDeniedHandler)

            // enable h2-console
            .and()
            .headers()
            .frameOptions()
            .sameOrigin()

            // 세션을 사용하지 않기 때문에 STATELESS로 설정
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

            .and()
            .authorizeRequests()
            .antMatchers("/api/member/join").permitAll()
            .antMatchers("/api/products").permitAll()
            .antMatchers("/api/product/**").permitAll()
            .antMatchers("/api/member/register").permitAll()

            .anyRequest().authenticated();
    }
}