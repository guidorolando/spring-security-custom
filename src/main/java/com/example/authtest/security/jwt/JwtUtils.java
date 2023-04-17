package com.example.authtest.security.jwt;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private int jwtExpirationMs;

    public String generateToken(Authentication authentication) {
        /*UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        User user = this.userRepository.findByLogin(userPrincipal.getUsername()).get();
        UserType userType = this.userTypeRepository.findById(user.getUserType().getId()).get();
        Sucursal sucursal = this.sucursalRepository.findById(user.getSucursal().getId()).get();*/
        Map<String, Object> claims = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        /*$customClaims = array(
                'name' => $user->nombre,
                'tipo_usuario_id' => $user->tipo_usuario_id,
                'user_id' => $user->id,
                'login' => $user->login,
                'sucursal_id' => $user->sucursal_id,
                'sucursal' => $sucursal->nombre,
                'role' => $role->nombre
        );*/
        /*data.put("name", user.getName());
        data.put("tipo_usuario_id", userType.getId());
        data.put("user_id", user.getId());
        data.put("login", user.getLogin());
        data.put("sucursal_id", sucursal.getId());
        data.put("sucursal", sucursal.getName());
        data.put("role", userType.getName());*/
        claims.put("data", data);
        return Jwts.builder()
                //.setSubject((userPrincipal.getUsername()))
                .setSubject(("guido"))
                .setIssuedAt(new Date())
                .setClaims(claims)
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }
}