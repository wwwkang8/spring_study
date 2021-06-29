package com.kosta.jwt;

import com.sun.org.apache.xml.internal.security.algorithms.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;

@Service
public class ServiceSecurityImpl implements SecurityService {

    private static final String SECRET_KEY = "aasjjkjaskjdl1k2naskjkdakj34c8sa";

    @Override
    public String createToken(String subject, long ttlMillis){

        if(ttlMillis <= 0){
            throw new RuntimeException("Expiry time must be greater than zero: ["+ttlMillis+"]");
        }

        SignatureAlgorithm  signatureAlgorithm= SignatureAlgorithm.HS256;

        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJCEAlgorithmString())

        return "";
    }

    @Override
    public String getSubject(String token){

        return "";
    }

}
