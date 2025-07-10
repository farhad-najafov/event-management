package com.example.eventmanagement.controller;

import com.example.eventmanagement.model.request.AuthenticationRequest;
import com.example.eventmanagement.model.response.AuthenticationResponse;
import com.example.eventmanagement.security.CustomUserDetailsService;
import com.example.eventmanagement.security.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthenticationManager authenticationManager;
  private final JwtUtil jwtUtil;
  private final CustomUserDetailsService customUserDetailsService;

  @PostMapping("/login")
  public ResponseEntity<AuthenticationResponse> login(@RequestBody
                                                      AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
    );

    final UserDetails userDetails = customUserDetailsService.loadUserByUsername(request.getUsername());
    final String accessToken = jwtUtil.generateAccessToken(userDetails);
    final String refreshToken = jwtUtil.generateRefreshToken(userDetails);
    return ResponseEntity.ok(
        new AuthenticationResponse(accessToken, refreshToken)
    );
  }

  @PostMapping("/refreshToken")
  public ResponseEntity<AuthenticationResponse> refreshToken(HttpServletRequest request) {

    final String authorizationHeader = request.getHeader("Authorization");
    if (authorizationHeader == null && !authorizationHeader.startsWith("Bearer ")) {
      return ResponseEntity.badRequest().build();
    }

    final String refreshToken = authorizationHeader.substring(7);
    final String username = jwtUtil.extractUsername(refreshToken);

    UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

    if(jwtUtil.isTokenValid(refreshToken, userDetails)) {
      String newAccessToken = jwtUtil.generateAccessToken(userDetails);
      return  ResponseEntity.ok(
          new AuthenticationResponse(newAccessToken, refreshToken));

    }else {
      return ResponseEntity.status(403).build();
    }

  }


}
