package com.vectorx.springsecurity.util;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

/**
 * @Description: MyPasswordEncoder
 * @Author: VectorX
 * @Date: 2022/7/30 18:33
 * @Version: V1.0
 */
@Component
public class MyPasswordEncoder implements PasswordEncoder
{
    @Override
    public String encode(CharSequence rawPassword) {
        return md5(rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return Objects.equals(md5(rawPassword), encodedPassword);
    }

    private String md5(CharSequence password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] input = String.valueOf(password).getBytes();
            byte[] output = digest.digest(input);
            String encrypted = new BigInteger(1, output).toString(16);
            return encrypted.toUpperCase();
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
