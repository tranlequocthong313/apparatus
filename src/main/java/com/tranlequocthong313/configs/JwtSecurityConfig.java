/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.configs;

import com.tranlequocthong313.filters.CustomAccessDeniedHandler;
import com.tranlequocthong313.filters.JwtAuthenticationTokenFilter;
import com.tranlequocthong313.filters.RestAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author tranlequocthong313
 */
@Configuration
@EnableWebSecurity
@EnableTransactionManagement
@ComponentScan(basePackages = {
	"com.tranlequocthong313.controllers",
	"com.tranlequocthong313.repositories",
	"com.tranlequocthong313.services",})
@Order(1)
@PropertySource("classpath:env.properties")
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() throws Exception {
		JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter = new JwtAuthenticationTokenFilter();
		jwtAuthenticationTokenFilter.setAuthenticationManager(authenticationManager());
		return jwtAuthenticationTokenFilter;
	}

	@Bean
	public RestAuthenticationEntryPoint restServicesEntryPoint() {
		return new RestAuthenticationEntryPoint();
	}

	@Bean
	public CustomAccessDeniedHandler customAccessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}

	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers("/api/users/login/**", "/api/users/register/**").permitAll()
			.antMatchers(HttpMethod.GET, "/api/thread-categories/**", "/api/threads/**", "/api/replies/**").permitAll()
			.antMatchers(HttpMethod.POST, "/api/thread-categories/**").hasRole("ADMIN")
			.antMatchers(HttpMethod.PATCH, "/api/thread-categories/**").hasRole("ADMIN")
			.antMatchers(HttpMethod.DELETE, "/api/thread-categories/**").hasRole("ADMIN")
			.anyRequest().authenticated()
			.and()
			.httpBasic().authenticationEntryPoint(restServicesEntryPoint()).and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.exceptionHandling().accessDeniedHandler(customAccessDeniedHandler()).and()
			.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}
