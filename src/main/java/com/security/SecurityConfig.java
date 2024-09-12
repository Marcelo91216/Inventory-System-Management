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

/**
 * All the security configurations of the web service application.
 * 
 * @author Marcelo Eduardo Guillen Castillo
 */
@Configuration
public class SecurityConfig {
	/**
	 * The CEO's password.
	 */
	@Value("${file.input.ceo}")
	private String ceo;

	/**
	 * The employee's password.
	 */
	@Value("${file.input.employee}")
	private String employee;

	/**
	 * Manages the details of the all the user's on the service, in this case all
	 * this info is storage in memory.
	 * 
	 * @return The manager object of the user details in memory.
	 */
	@Bean
	public InMemoryUserDetailsManager userDetailManager() {
		UserDetails employee = User.builder().username("employee").password("{noop}" + this.employee).roles("EMPLOYEE")
				.build();

		UserDetails ceo = User.builder().username("ceo").password("{noop}" + this.ceo).roles("CEO", "EMPLOYEE").build();
		return new InMemoryUserDetailsManager(employee, ceo);
	}

	/**
	 * Configures the filters of the users. Authorization configuration.
	 * 
	 * @param http The HTTP security.
	 * @return The security filter chain.
	 */
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
