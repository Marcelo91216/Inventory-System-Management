package com.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	@Value("${file.input.ceo}")
	private String ceo;

	@Value("${file.input.employee}")
	private String employee;

	@Bean
	public InMemoryUserDetailsManager userDetailManager() {
		UserDetails employee = User.builder().username("employee").password("{noop}" + this.employee).roles("EMPLOYEE")
				.build();

		UserDetails ceo = User.builder().username("ceo").password("{noop}" + this.ceo).roles("CEO", "EMPLOYEE").build();
		return new InMemoryUserDetailsManager(employee, ceo);
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(configurer -> configurer.requestMatchers(HttpMethod.GET, "/**").hasRole("EMPLOYEE")
				.requestMatchers(HttpMethod.POST, "/**").hasRole("EMPLOYEE").requestMatchers(HttpMethod.PUT, "/**")
				.hasRole("EMPLOYEE").requestMatchers(HttpMethod.DELETE, "/**").hasRole("CEO"));

		http.httpBasic(Customizer.withDefaults());

		http.csrf(csrf -> csrf.disable());

		return http.build();
	}
}
