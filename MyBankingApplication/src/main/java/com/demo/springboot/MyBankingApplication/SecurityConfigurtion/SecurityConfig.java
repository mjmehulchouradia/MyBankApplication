package com.demo.springboot.MyBankingApplication.SecurityConfigurtion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private JwtFilter jwtFilter;

	
	@Bean
	public SecurityFilterChain handleSecurity(HttpSecurity http) throws Exception 
	{
//		 http
//         .csrf().disable()
//         .authorizeRequests()
//         .anyRequest().authenticated()
//         .and()
//         .httpBasic();
// return http.build();
		 
		 http.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(req -> {req.requestMatchers("/api/login").permitAll();
			req.requestMatchers("/api/register").permitAll();
			req.anyRequest().authenticated();
			})
			.httpBasic(Customizer.withDefaults())
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class);
 return http.build();
    }
	
	@Bean
	public PasswordEncoder pwdEncode()
	{
		return new BCryptPasswordEncoder();
	}
	
}
