package com.be.authenticate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/swagger-ui/**", "/v3/api-docs/**");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(PUBLIC_LIST)
                .permitAll()
                .antMatchers(ADMIN_LIST_PRIVATE)
                .hasAnyRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .logout().clearAuthentication(true).invalidateHttpSession(true)
                .and()
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "HEAD", "OPTIONS"));
        configuration.setAllowCredentials(false);
        configuration.setAllowedHeaders(Arrays.asList("Authorization","Cache-Control","Content-Type"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    String[] PUBLIC_LIST = {
//            "/api/test/post","/api/test/get/*","/api/test/put/*","/api/test/del/*",
            "/api/upload","/api/files/*",
            "/api/nguoi-dung/create","/api/nguoi-dung/login","/api/nguoi-dung/get/*","/api/nguoi-dung/put/*","/api/nguoi-dung/del/*","/api/nguoi-dung/change-pass/*",
            "/api/lop-hoc/post","/api/lop-hoc/get/*","/api/lop-hoc/put/*","/api/lop-hoc/del/*",
            "/api/hoi-dong/post","/api/hoi-dong/get/*","/api/hoi-dong/put/*","/api/hoi-dong/del/*",
            "/api/thanh-vien-hoi-dong/post","/api/thanh-vien-hoi-dong/get/*","/api/thanh-vien-hoi-dong/put/*","/api/thanh-vien-hoi-dong/del/*",
            "/api/de-tai/post","/api/de-tai/get/*","/api/de-tai/put/*","/api/de-tai/del/*",
    };

    String[] ADMIN_LIST_PRIVATE = {
    };

}
