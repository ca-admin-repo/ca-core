package com.ca.provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtTokenProvider {

  private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);
  private final SecretKey secretKey;

  @Value("${jwt.expiration}")
  private Long jwtExpiration;

  public JwtTokenProvider(SecretKey secretKey) {
    this.secretKey = secretKey;
  }

  public String createToken(Long userId) {

    String jwtToken =
        Jwts.builder()
            .subject(userId.toString())
            .issuedAt(new Date())
            .expiration(new Date(new Date().getTime() + jwtExpiration))
            .signWith(this.secretKey)
            .compact();
    logger.debug("JWT Token for userId {}: {}", userId, jwtToken);
    return jwtToken;
  }
}
