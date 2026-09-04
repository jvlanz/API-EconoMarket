package com.economarket.api.services;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	private final String SECRET = "minha-chave-secreta-para-jwt-1234567890";
	//private final String SECRET = "minha-chave";

	private final long EXPIRATION = 1000 * 60 * 60;

	private SecretKey getKey() {
		return Keys.hmacShaKeyFor(SECRET.getBytes());
	}

	public String gerarToken(String email, String role, String nome, Long id) {
		return Jwts.builder().subject(email).claim("role", role).claim("nome", nome).claim("id", id).issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + EXPIRATION)).signWith(getKey()).compact();
	}

	public String extrairEmail(String token) {
		return Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token).getPayload().getSubject();
	}

	public String extractRole(String token) {
		Claims claims = getClaims(token);
		return claims.get("role", String.class);
	}

//	public String extrairId(String token) {
//		Claims claims = getClaims(token);
//		return claims.get("id", Long.class);
//	}

	private Claims getClaims(String token) {

		return Jwts.parser()

				.verifyWith(getKey())

				.build()

				.parseSignedClaims(token)

				.getPayload();
	}
}
