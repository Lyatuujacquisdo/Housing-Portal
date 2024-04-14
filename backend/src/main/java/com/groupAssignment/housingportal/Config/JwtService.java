package com.groupAssignment.housingportal.Config;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    public String extractRegistrationNo(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimResolver){
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails){
            return generateToken(new HashMap<>(), userDetails);
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
            final String registrationNo = extractRegistrationNo(token);
            //getUsername is a native function to the UserDetails class
            //is the username we have as input the same as the one on the token
            return (registrationNo.equals(userDetails.getUsername() )) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ){
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*30))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) //same key will be used to sign all claims and obtain signature that ensures that a token is really issued by the issuer(in this case the server)
                .compact(); //generates and returns the token
    }

    //extract all the claims
    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()  //TO PARSE TOKEN
                .setSigningKey(getSigningKey())//SETTING SIGNING KEY , DIGITALLY SIGN WITH IT
                .build()
                .parseClaimsJws(token)
                .getBody();  //GETTING ALL THE CLAIMS
    }

    private Key getSigningKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }



}
