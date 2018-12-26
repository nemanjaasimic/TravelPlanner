package com.simic.travel_planner.security;

import com.simic.travel_planner.exception.RoleNotFoundException;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    private final String jwtAuthSecret;

    private final Long jwtExpirationInMs;

    @Autowired
    public JwtTokenProvider(@Value("${app.jwtSecret}")String jwtAuthSecret,
                            @Value("${app.jwtExpirationInMs}") Long jwtExpirationInMs) {
        this.jwtAuthSecret = jwtAuthSecret;
        this.jwtExpirationInMs = jwtExpirationInMs;
    }

    public String generateAuthToken(Authentication authentication) {

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);
        Claims claims = Jwts.claims().setSubject(Long.toString(userPrincipal.getId()));
        claims.put("role", userPrincipal.getAuthorities()
                .stream()
                .findFirst()
                .orElseThrow(() -> new RoleNotFoundException("Role not found!"))
                .getAuthority());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtAuthSecret)
                .compact();
    }

    public Integer getUserIdFromJwt(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtAuthSecret)
                .parseClaimsJws(token)
                .getBody();

        return Integer.parseInt(claims.getSubject());
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtAuthSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            logger.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty.");
        }
        return false;
    }
}
