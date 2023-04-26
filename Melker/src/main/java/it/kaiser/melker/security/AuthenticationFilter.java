package it.kaiser.melker.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.kaiser.melker.model.UserLoginRequest;



public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	public AuthenticationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {

		try {

			UserLoginRequest creds = new ObjectMapper()
					.readValue(req.getInputStream(), UserLoginRequest.class);

			return getAuthenticationManager()
					.authenticate(
							new UsernamePasswordAuthenticationToken(
									creds.getEmail(), 
									creds.getPassword(), new ArrayList<>()));

		} catch (IOException io) {
			throw new RuntimeException(io);

		}

	}
	
	
	@Override
	protected void successfulAuthentication(HttpServletRequest req,
			HttpServletResponse res, FilterChain chain,Authentication authResult) throws IOException, ServletException{
		
		
		
	} 
	
	

}
