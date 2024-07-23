package com.demo.springboot.MyBankingApplication.UtilPackage;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	

	   @Value("${security.jwt.secret-key}")
	    private String SECRET_KEY;

	    @Value("${security.jwt.expiration-time}")
	    private Long jwtExpiration;
	
	
	//Signing the Secret Key
	private SecretKey getSigningKey()
	{
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
	}

	//Get Username From the token
	public String getUserNameFromToken(String token)
    {
    	return Jwts.parser()
    			.verifyWith(getSigningKey())
    			.build()
    			.parseSignedClaims(token)
    			.getPayload().getSubject();
    }
	
	//Generate JWT Token
	public String generateToken(Authentication auth)
    {
    	
    	String token=Jwts.builder()
    			.subject(auth.getName())
    			.header().empty().add("typ","JWT")
                .and()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSigningKey(),Jwts.SIG.HS256)
                .compact();
    	
    	return token;
    }
	
	
	  public Long getExpirationTime() {
	       return jwtExpiration;
}
	  
	    //Validate Token
	    public boolean validateToken(String token)
	    {
	    	try{Jwts.parser()
			.verifyWith(getSigningKey())
			.build()
			.parse(token);
	    	
	    	return true;
	    	}
	    	catch(Exception e)
	    	{
	    		return false;
	    	}
	    }
}
