package com.fetchsaudata.securityConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

//	@Bean
//	public SecurityFilterChain filter(HttpSecurity http) throws Exception {
//		http.csrf().disable();
//		http.authorizeHttpRequests().antMatchers("/actuator/health").permitAll().anyRequest().authenticated()
////		.and()
////				.oauth2Login()
//				.and().oauth2ResourceServer().jwt(); // to redirect to oauth2 login page.
//		http.cors();
//		return http.build();
//	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors();
		http.authorizeRequests()
		.antMatchers("/actuator/**").permitAll()
		.anyRequest().authenticated().and().oauth2ResourceServer().jwt();
	}
}
