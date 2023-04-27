package it.kaiser.melker.security;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import it.kaiser.melker.model.UserDTO; 
import it.kaiser.melker.model.UserLoginRequest;
import it.kaiser.melker.service.UserService;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private UserService userService;
	private Environment env;

	public AuthenticationFilter(UserService userService, 
			Environment env,
			AuthenticationManager authenticationManager) {
		super(authenticationManager);
		this.userService = userService;
		this.env = env;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req,
			HttpServletResponse res)
			throws AuthenticationException {

		try {

			UserLoginRequest creds = new ObjectMapper().readValue(req.getInputStream(), 
					UserLoginRequest.class);

			return getAuthenticationManager().authenticate(
					new UsernamePasswordAuthenticationToken(creds.getEmail(), 
							creds.getPassword(), new ArrayList<>()));

		} catch (IOException io) {
			throw new RuntimeException(io);

		}

	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req,
			HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {

		String username = ((User) auth.getPrincipal()).getUsername();
		UserDTO userDetails = userService.findByEmail(username);
		
		
		String tokenSecret = env.getProperty("token.secret");
		byte[] secretKeyBytes = Base64.getEncoder().encode(tokenSecret.getBytes());
		SecretKey secretKey = new SecretKeySpec(secretKeyBytes, SignatureAlgorithm.HS512.getJcaName());
		Instant now = Instant.now();
		String token = Jwts.builder()
		.setSubject(userDetails.getUserId())
		.setExpiration(Date.from(now.plusMillis(Long.parseLong(env
				.getProperty("token.expiration_time")))))
		.setIssuedAt(Date.from(now))
		.signWith(secretKey, SignatureAlgorithm.HS512)
		.compact();
		
		res.addHeader("token", token);
		res.addHeader("userId", userDetails.getUserId());
		
	}

}
