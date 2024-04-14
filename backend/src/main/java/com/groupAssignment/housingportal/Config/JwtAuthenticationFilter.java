package com.groupAssignment.housingportal.Config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor //make a constructor using any declared final field
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
private final JwtService jwtService;

    @Autowired
    private final UserDetailsService userDetailsService;

    Logger logger = LoggerFactory.getLogger(JwtService.class);

    @Override
    protected void doFilterInternal(
           @NonNull HttpServletRequest request,
           @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain //calls the next filter within the chain
    ) throws ServletException, IOException {
        try{
            final String authHeader = request.getHeader("Authorization");
            final String jwt;
            final String registrationNo;

            logger.info("AUTHORIZATION_HEADER {}", authHeader);

            if(authHeader == null || !authHeader.startsWith("Bearer ")){
                filterChain.doFilter(request,response);
                return;
            }
            jwt = authHeader.substring(7);
            registrationNo =  jwtService.extractRegistrationNo(jwt); //extract email from the JWT token

            if(registrationNo != null && SecurityContextHolder.getContext().getAuthentication() == null ){
                //check if user is already authenticated so that it doesn't have to go through the below process again and again for same request session
                System.out.println("it goes through the filter");

                //getting the user from the database
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(registrationNo);

                if(jwtService.isTokenValid(jwt, userDetails)){
                    UsernamePasswordAuthenticationToken authToken= new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }

            }
            filterChain.doFilter(request,response);//pass it to next filter
        } catch (Exception e){
            System.out.println("An exception occurred: " + e.getMessage());
        }
        }

}
