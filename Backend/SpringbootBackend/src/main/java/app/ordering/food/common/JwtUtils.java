package app.ordering.food.common;

import app.ordering.food.entity.Merchant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;

@Component
public class JwtUtils {

    public final static SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;

    private static final String HS512KEY = "PYKddQQ5lQpMFaj7lgiFWpqRujorSyL6pDjadqJE4TA";


    public static String createToken(Merchant merchant) {
        if (merchant == null || merchant.getId() == null || merchant.getPhone() == null) {
            return null;
        }
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(HS512KEY);
        Key    signingkey        = new SecretKeySpec(apiKeySecretBytes, SIGNATURE_ALGORITHM.getJcaName());
        return Jwts.builder()
                .setSubject(String.valueOf(merchant.getId()))
                .claim("id", merchant.getId())
                .claim("phone", merchant.getPhone())
                .signWith(SIGNATURE_ALGORITHM, signingkey)
                .compact();
    }

    public static Integer getMerchantId(String token) {
        if (token == null) {
            return null;
        }
        try {
            byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(HS512KEY);
            Key    signingkey        = new SecretKeySpec(apiKeySecretBytes, SIGNATURE_ALGORITHM.getJcaName());
            Claims claims = Jwts.parser()
                    .setSigningKey(signingkey)
                    .parseClaimsJws(token)
                    .getBody();
            return claims.get("id", Integer.class);
        } catch (Exception e) {
            return null;
        }
    }
}
