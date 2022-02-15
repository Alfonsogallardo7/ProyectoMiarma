package com.salesianostriana.dam.MiarmaApi.security;

import com.salesianostriana.dam.MiarmaApi.security.jwt.JwtAccesDeniedHandler;
import com.salesianostriana.dam.MiarmaApi.security.jwt.JwtAuthenticationEntryPoint;
import com.salesianostriana.dam.MiarmaApi.security.jwt.JwtAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccesDeniedHandler jwtAccesDeniedHandler;
    private final JwtAuthorizationFilter jwtAuthorizationFilter;



    @Override
    protected void configure (AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccesDeniedHandler)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/auth/login", "/auth/register").permitAll()
                .antMatchers(HttpMethod.GET, "/me", "/post/public", "/post/{id}", "/post/{nick}", "/post/me", "/profile/{id}", "/follow/list").permitAll()
                .antMatchers(HttpMethod.POST, "/post/", "/follow/{nick}", "/follow/accept/{id}", "/follow/decline/{id}").permitAll()
                .antMatchers(HttpMethod.PUT,"/post/{id}", "/porfile/me").hasRole("USUARIO")
                .antMatchers(HttpMethod.DELETE, "/post/{id}").hasRole("USUARIO")
                .anyRequest().authenticated();

        http.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

        http.headers().frameOptions().disable();

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
