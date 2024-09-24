package com.pksconnect.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private FetchEmployee fetchEmployee;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authToken = request.getHeader("Authorization");
		String username = null;
		String token = null;
		
		
		if(authToken != null && authToken.startsWith("Bearer "))
		{
			 token = authToken.substring(7);
			 username = jwtService.extractUser(token);
		}
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null)
		{
			UserDetails userDetails = fetchEmployee.loadUserByUsername(username);
			
			if(jwtService.validateToken(token))
			{
				UsernamePasswordAuthenticationToken getToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				
				SecurityContextHolder.getContext().setAuthentication(getToken);
			}
			
		}
		
		filterChain.doFilter(request, response);
        
    }



}
