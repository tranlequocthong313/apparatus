/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.configs;

import com.tranlequocthong313.components.JwtFilter;
import com.tranlequocthong313.filters.CustomAccessDeniedHandler;
import com.tranlequocthong313.filters.RestAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author tranlequocthong313
 */
@Configuration
@EnableWebSecurity
@EnableTransactionManagement
@ComponentScan(basePackages = {
	"com.tranlequocthong313.components",
	"com.tranlequocthong313.controllers",
	"com.tranlequocthong313.repositories",
	"com.tranlequocthong313.services",
	"com.tranlequocthong313.utils"
})
@Order(2)
@PropertySource("classpath:env.properties")
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private Environment env;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtFilter jwtFilter;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder builder) throws Exception {
		builder.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}

	@Bean
	public RestAuthenticationEntryPoint restServicesEntryPoint() {
		return new RestAuthenticationEntryPoint();
	}

	@Bean
	public CustomAccessDeniedHandler customAccessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}

	@Override
	protected void configure(HttpSecurity http)
		throws Exception {
		http.csrf().disable()
			.formLogin(form -> form
				.loginPage("/users/login")
				.defaultSuccessUrl("/")
				.loginProcessingUrl("/users/login")
				.failureUrl("/users/login?error")
				.permitAll()
			)
			.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		http.logout().logoutUrl("/users/logout").logoutSuccessUrl("/users/login?logout").permitAll();
		http.exceptionHandling().accessDeniedPage("/login?accessDenied");
		http.rememberMe(rememberMe -> rememberMe.key(env.getProperty("system.secret_token")).tokenValiditySeconds(86400));

		http
			.authorizeRequests()
			.antMatchers(HttpMethod.OPTIONS).permitAll()
			.antMatchers("/api/users/login/**", "/api/users/register/**").permitAll()
			.antMatchers("/users/login", "/users/logout", "/assets/**").permitAll()
			.antMatchers(HttpMethod.GET, "/api/thread-categories/**", "/api/threads/**", "/api/replies/**").permitAll()
			.antMatchers(HttpMethod.POST, "/api/thread-categories/**").hasRole("ADMIN")
			.antMatchers(HttpMethod.PATCH, "/api/thread-categories/**").hasRole("ADMIN")
			.antMatchers(HttpMethod.DELETE, "/api/thread-categories/**").hasRole("ADMIN")
			.antMatchers("/api/**").authenticated()
			.antMatchers("/**").hasAnyRole("WORKER", "ADMIN")
			.and()
			.httpBasic().authenticationEntryPoint(restServicesEntryPoint()).and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.exceptionHandling().accessDeniedHandler(customAccessDeniedHandler()).and()
			.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	}
}
