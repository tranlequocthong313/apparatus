/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author tranlequocthong313
 */
@Configuration
@EnableWebSecurity
@EnableTransactionManagement
@ComponentScan(basePackages = {
        "com.tranlequocthong313.controllers",
        "com.tranlequocthong313.repositories",
        "com.tranlequocthong313.services"
})
@Order(2)
@PropertySource("classpath:env.properties")
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private Environment env;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http)
            throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/users/login", "/users/logout", "/assets/**", "/api/**")
                .permitAll()
                .antMatchers("/**").hasRole("ADMIN")
                .and()
                .formLogin(form -> form
                        .loginPage("/users/login")
                        .defaultSuccessUrl("/")
                        .loginProcessingUrl("/users/login")
                        .failureUrl("/users/login?error")
                        .permitAll()
                );
        http.logout().logoutUrl("/users/logout").logoutSuccessUrl("/users/login?logout").permitAll();
        http.exceptionHandling().accessDeniedPage("/login?accessDenied");
        http.rememberMe(rememberMe -> rememberMe.key(env.getProperty("jwt.secret_token")).tokenValiditySeconds(86400));
    }
}
