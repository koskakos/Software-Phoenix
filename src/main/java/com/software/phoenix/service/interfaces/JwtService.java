package com.software.phoenix.service.interfaces;

import com.software.phoenix.model.RefreshToken;
import com.software.phoenix.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public interface JwtService {
    public String extractUserName(String token);
    public String generateAccessToken(UserDetails userDetails);
    RefreshToken generateRefreshToken(User user);
    boolean isTokenValid(String token, UserDetails userDetails);
}
