package com.pksconnect.security;

import java.util.Date;

import javax.crypto.SecretKey;


import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.function.Function;

@Service
public class JwtService {

    String key = "uewiorueiourioewurkdsjfhjkdshfsdjkhfsdjkncmvbxcnmvcxnvxcnhewuhfurhfurhd";

    public String generateToken(String email) {

        return Jwts.builder()
               .subject(email)
               .issuedAt(new Date(System.currentTimeMillis()))
               .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
               .signWith(getKey())
               .compact();
        
    }

    public SecretKey getKey()
    {
        byte arr[] = key.getBytes();
        return Keys.hmacShaKeyFor(arr);
    }

      public String extractUser(String token) {
	        // extract the username from jwt token
	        return extractClaim(token, Claims::getSubject);
	    }

	    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
	        final Claims claims = extractAllClaims(token);
	        return claimResolver.apply(claims);
	    }

	    private Claims extractAllClaims(String token) {
	        return Jwts.parser()
	                .verifyWith(getKey())
	                .build()
	                .parseSignedClaims(token)
	                .getPayload();
	    }

	    public boolean validateToken(String token) {
	      
	        return (!isTokenExpired(token));
	    }

	    private boolean isTokenExpired(String token) {
	        return extractExpiration(token).before(new Date());
	    }

	    private Date extractExpiration(String token) {
	        return extractClaim(token, Claims::getExpiration);
	    }
	

    

}
