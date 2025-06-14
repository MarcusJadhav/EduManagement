package com.example.Admin.Security;

import com.example.Admin.Entities.Admin;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "8e332379409f146a7a071faefbc6ff3a06765fbe07648d5d94f5fb5c99a7f69e45a2be2f136c9b2ce9611ba5cd1cb28f1bd56d53828975a5294549becd2885427a791455bcc24d865dc257898f56f7df419d9e002dceff0b5ae1370b62204594dfb62c25b1c5668cf4374dea53a1682bcc3ec6bfe3c9d09a5e60798585a25b6b1122e7984a4f305b1d2dbfa47482507690569d2e1badbfbc6858f7286404d55fa0dc547de53a3dda238a5bd94f4e55d6092fe4f3afce1f5866651e8b4586c9b515244a6af46e2c24c246d0e2fd60688a3997525e1d9a5e5782688614444a985ee1be253236e28062aea2ce9c44bc6ce39c7ace33e379edec82ef1a054efa955f";

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        return extractUsername(token).equals(userDetails.getUsername());
    }

    public String generateToken(Admin admin) {
        return generateToken(admin.getUsername());
    }

}
