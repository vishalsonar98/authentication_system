package com.authentication_system.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.authentication_system.service.BlacklistTokenService;
import com.authentication_system.service.impl.CustomUserDetailsService;
import com.authentication_system.util.JwtUtil;

/**
 * Provides implementation for OncePerRequestFilter class. Using
 * doFilterInternal method checks whether jwt token is valid or not.
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	@Autowired
	private BlacklistTokenService blacklistTokenService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String header = request.getHeader("Authorization");
		String username = null;
		String jwtToken = null;
		if (header != null && header.startsWith("Bearer ")) {
			jwtToken = header.substring(7);
			if (blacklistTokenService.isTokenBlacklisted(jwtToken)) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				return;
			}
			try {
				username = this.jwtUtil.extractUsername(jwtToken);

			} catch (Exception e) {
				e.printStackTrace();
			}

			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails user = this.customUserDetailsService.loadUserByUsername(username);
				if (jwtUtil.validateToken(jwtToken, user)) {
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
							user, null, user.getAuthorities());
					usernamePasswordAuthenticationToken
							.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
			} else {
				System.out.println("Token not validated");
			}
		}

		filterChain.doFilter(request, response);
	}

}
