package com.ty.EcomApp.Config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.ty.EcomApp.Service.CustomUserDetailServices;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	private final CustomUserDetailServices services;
	
	public SecurityConfig(CustomUserDetailServices services) {
		this.services = services;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	
		http.authorizeHttpRequests(req -> req
		        .requestMatchers("/login").permitAll() // login and logout no need authentication
		        .anyRequest().authenticated()); //all request need authentication
		//custom login Html page setup
		http.formLogin(form -> form.loginPage("/login"));
		//logout added
		 http.logout(logout -> logout.logoutUrl("/logout"));  
		

		
		return http.build();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		//plain password
		provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		provider.setUserDetailsService(services);
		return provider;
	}
	
	
}
