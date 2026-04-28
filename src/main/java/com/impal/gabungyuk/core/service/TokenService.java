package com.impal.gabungyuk.core.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class TokenService {

    private final String tokenSecret;

    public TokenService(@Value("${app.auth.token-secret:change-this-secret-in-production}") String tokenSecret) {
        this.tokenSecret = tokenSecret;
    }

    public String generateToken(Integer userId, long expiredAt) {
        String payload = userId + ":" + expiredAt;
        String signature = hmacSha256(payload);
        String rawToken = payload + ":" + signature;
        return Base64.getUrlEncoder().withoutPadding().encodeToString(rawToken.getBytes(StandardCharsets.UTF_8));
    }

    public Integer extractUserIdFromAuthorizationHeader(String authorizationHeader) {
        if (authorizationHeader == null || authorizationHeader.isBlank()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Missing Authorization header");
        }

        String token = authorizationHeader.trim();

        // Accept both "Bearer <token>" and raw token values to be more tolerant to clients
        if (token.startsWith("Bearer ")) {
            token = token.substring(7).trim();
        }

        // strip possible surrounding quotes that some clients might include
        if ((token.startsWith("\"") && token.endsWith("\"")) || (token.startsWith("'") && token.endsWith("'"))) {
            token = token.substring(1, token.length() - 1).trim();
        }

        if (token.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Missing token");
        }

        return extractUserIdFromToken(token);
    }

    private Integer extractUserIdFromToken(String token) {
        try {
            String decoded = new String(Base64.getUrlDecoder().decode(token), StandardCharsets.UTF_8);

            // parse token in a robust way: userId:expiredAt:signature
            int firstColon = decoded.indexOf(':');
            int lastColon = decoded.lastIndexOf(':');
            if (firstColon == -1 || lastColon == -1 || firstColon == lastColon) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token");
            }

            String userIdPart = decoded.substring(0, firstColon);
            String expiredAtPart = decoded.substring(firstColon + 1, lastColon);
            String signature = decoded.substring(lastColon + 1);

            int userId = Integer.parseInt(userIdPart);
            long expiredAt = Long.parseLong(expiredAtPart);

            String payload = userIdPart + ":" + expiredAtPart;
            String expectedSignature = hmacSha256(payload);

            if (!expectedSignature.equals(signature)) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token signature");
            }

            if (System.currentTimeMillis() > expiredAt) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token has expired");
            }

            return userId;
        } catch (IllegalArgumentException exception) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token");
        }
    }

    private String hmacSha256(String value) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec keySpec = new SecretKeySpec(tokenSecret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            mac.init(keySpec);
            byte[] hash = mac.doFinal(value.getBytes(StandardCharsets.UTF_8));
            return Base64.getUrlEncoder().withoutPadding().encodeToString(hash);
        } catch (Exception exception) {
            throw new IllegalStateException("Failed to sign token", exception);
        }
    }
}
