package com.example.demo.security;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUserProviderToken extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authManager;

	public JwtUserProviderToken(AuthenticationManager authManager) {
		this.authManager = authManager;
	}

	private final String JWT_SECRET = "JwtSecret";
	private final long JWT_EXPIRATION = 604800000L;
	private final String prefix = "Bearer";
	private final String header = "Authorization";

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			UserFromCl userCL = new ObjectMapper().readValue(request.getInputStream(), UserFromCl.class);
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
					userCL.getUserName(), userCL.getPassWord(), Collections.emptyList());

			return authManager.authenticate(authToken);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		Long now = System.currentTimeMillis();
		String token = Jwts.builder().setSubject(auth.getName())
				.claim("authorities",
						auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(now)).setExpiration(new Date(now + JWT_EXPIRATION * 1000))
				.signWith(SignatureAlgorithm.HS512, JWT_SECRET.getBytes()).compact();

		response.addHeader(header, prefix + token);

	}

	private static class UserFromCl {
		private String userName;

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getPassWord() {
			return passWord;
		}

		public void setPassWord(String passWord) {
			this.passWord = passWord;
		}

		private String passWord;
	}

}
