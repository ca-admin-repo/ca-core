package com.ca.intercepter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.crypto.SecretKey;
import java.util.ArrayList;

@Component
public class JwtInterceptor implements HandlerInterceptor {

  private final SecretKey secretKey;

  public JwtInterceptor(SecretKey secretKey) {
    this.secretKey = secretKey;
  }

  @Override
  public boolean preHandle(
      HttpServletRequest request, HttpServletResponse response, Object handler) {
    String authorizationHeader = request.getHeader("Authorization");

    if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      return false;
    }
    String token = authorizationHeader.substring(7);
    try {
      Jws<Claims> jws = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
      String userId = jws.getPayload().getSubject();
      request.setAttribute("userId", userId);

      Authentication authentication =
          new UsernamePasswordAuthenticationToken(userId, null, new ArrayList<>());
      SecurityContextHolder.getContext().setAuthentication(authentication);
    } catch (JwtException e) {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      return false;
    }
    return true;
  }
}
