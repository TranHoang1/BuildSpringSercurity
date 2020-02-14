package com.example.demo.security;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtFillterAuthentication extends OncePerRequestFilter {

	private final String JWT_SECRET = "JwtSecret";
	@SuppressWarnings("unused")
	private final long JWT_EXPIRATION = 604800000L;
	private final String prefix = "Bearer";
	private final String header = "Authorization";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String headerRq = request.getHeader(header);
		if (headerRq == null /*|| !headerRq.equals(prefix)*/) {
			filterChain.doFilter(request, response);
			return;
		}
		String token = headerRq.replace(prefix, "");
		try {
			Claims claims = Jwts.parser().setSigningKey(JWT_SECRET.getBytes()).parseClaimsJws(token).getBody();
			String userName = claims.getSubject();
			@SuppressWarnings("unchecked")
			List<String> authorities = (List<String>) claims.get("authorities");
			if (userName != null) {
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userName, null,
						authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		} catch (Exception e) {
			SecurityContextHolder.clearContext();
		}
		filterChain.doFilter(request, response);
	}

}
