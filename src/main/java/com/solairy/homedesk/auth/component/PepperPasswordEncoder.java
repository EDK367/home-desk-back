package com.solairy.homedesk.auth.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Primary
@Component
public class PepperPasswordEncoder implements PasswordEncoder {

    private final BCryptPasswordEncoder bcrypt;
    private final String pepper;

    public PepperPasswordEncoder(@Value("${home-desk.pepper.secret}") String pepper) {
        this.pepper = pepper;
        this.bcrypt = new BCryptPasswordEncoder(12);
    }

    @Override
    public String encode(CharSequence password) {
        String combined = pepper + password;
        String hashedCombined = hashWithSha256(combined);
        return bcrypt.encode(hashedCombined);
    }

    @Override
    public boolean matches(CharSequence password, String encodedPassword) {
        String combined = pepper + password.toString();
        String hashedCombined = hashWithSha256(combined);
        if (bcrypt.matches(hashedCombined, encodedPassword)) {
            return true;
        }
        return bcrypt.matches(password, encodedPassword);
    }

    private String hashWithSha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing with SHA-256", e);
        }
    }
}
