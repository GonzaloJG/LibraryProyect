package com.booksApiApplication.booksapi.services;

import com.booksApiApplication.booksapi.converters.UserConverter;
import com.booksApiApplication.booksapi.dtos.LoginRequestDTO;
import com.booksApiApplication.booksapi.dtos.LoginResponseDTO;
import com.booksApiApplication.booksapi.entity.User;
import com.booksApiApplication.booksapi.repositories.UserRepository;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class UserService {
    @Value("${jwt.password}")
    private String jwtSecret;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private UserConverter userConverter;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(User user){
        User existUser = userRepo.findByUsername(user.getUsername())
                .orElse(null);

        if (existUser != null) throw  new RuntimeException("El usuario ya existe");

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public LoginResponseDTO login (LoginRequestDTO request){
        User user = userRepo.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario o password invalido"));

        if (! passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Usuario o password invalido");
        }

        String token = createToken(user);
        return new LoginResponseDTO(userConverter.fromEntity(user), token);
    }

    public String createToken(User user){
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + (1000*60*60));

        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (UnsupportedJwtException e) {
            log.error("JWT in a particular format/configuration that does not match the format expected\r\n"
                    + " * by the application");
        }catch (MalformedJwtException e) {
            log.error("JWT was not correctly constructed and should be rejected");
        }catch (SignatureException e) {
            log.error("signature or verifying an existing signature of a JWT failed");
        }catch (ExpiredJwtException e) {
            log.error("JWT was accepted after it expired and must be rejected");
        }
        return false;
    }

    public String getUsernameFromToken(String jwt) {
        try {
            return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt).getBody().getSubject();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("Invalid Token");
        }
    }
}
