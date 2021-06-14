package io.fdlessard.liveproject.validate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Configuration
public class SecurityConfiguration {

    @Value("${publicKey}")
    private String publicKey;

    public RSAPublicKey publicKey() {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            var key = Base64.getDecoder().decode(publicKey);
            var x509 = new X509EncodedKeySpec(key);
            return (RSAPublicKey) keyFactory.generatePublic(x509);
        } catch (Exception e) {
            throw new RuntimeException("Wrong public key");
        }
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {

        return httpSecurity.authorizeExchange()
                .anyExchange().authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt(c -> c.publicKey(publicKey()))
                .and().build();

    }
}
