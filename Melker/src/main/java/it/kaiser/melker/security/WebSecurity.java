package it.kaiser.melker.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import it.kaiser.melker.service.UserService;


@Configuration
@EnableWebSecurity
public class WebSecurity {
	
	private Environment environment;
	private UserService userService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	
	public WebSecurity(Environment environment, UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.environment = environment;
		this.userService = userService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
    
    
    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
    	
    	// Configure AuthenticationManagerBuilder
    	AuthenticationManagerBuilder authenticationManagerBuilder = 
    			http.getSharedObject(AuthenticationManagerBuilder.class);
        
    	
    	authenticationManagerBuilder.userDetailsService(userService)
    	.passwordEncoder(bCryptPasswordEncoder);
    	AuthenticationManager authenticationManager = authenticationManagerBuilder.build();
    	
    	
    	http.csrf().disable();
  
        http.authorizeHttpRequests()
        .antMatchers(HttpMethod.POST, "/users").permitAll()
        .and()
        .addFilter(new AuthenticationFilter(userService,environment,authenticationManager))
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
 
        http.headers().frameOptions().disable();
        return http.build();

    }
}
